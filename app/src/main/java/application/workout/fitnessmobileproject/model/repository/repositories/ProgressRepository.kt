package application.workout.fitnessmobileproject.model.repository.repositories

import android.util.Log
import application.workout.fitnessmobileproject.model.KtorClientInstance
import application.workout.fitnessmobileproject.model.models.Progress
import application.workout.fitnessmobileproject.model.models.SharedProgress
import application.workout.fitnessmobileproject.model.repository.routes.ProgressApi
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.utils.io.concurrent.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProgressRepository private constructor(val username: String, val password: String): ProgressApi {
    private val PROGRESS_ROOT = "http://10.0.2.2:8080/progress"
    private val USER_ROOT = "user"
    private val SHARE_ROOT = "share"
    private val SHARED_ROOT = "shared"
    private val client = KtorClientInstance.getInstance(username, password)
    override suspend fun getProgressWithId(id: Int): Progress {
        return withContext(Dispatchers.IO) {
            try {
                client.get("$PROGRESS_ROOT/$id") {
                    basicAuth(username = username, password = password)
                }.body<Progress>()
            } catch(exception: Exception) {
                throw exception
            }
        }
    }

    override suspend fun getProgressWithUserAndProgram(userId: Int, programId: Int): Progress {
        return withContext(Dispatchers.IO) {
            try {
                client.get(PROGRESS_ROOT) {
                    parameter("user", userId)
                    parameter("program", programId)
                    basicAuth(username = username, password = password)
                }.body<Progress>()
            } catch(exception: Exception) {
                throw exception
            }
        }
    }

    override suspend fun getProgressListWithUserId(userId: Int): List<Progress> {
        return withContext(Dispatchers.IO) {
            try {
                client.get("$PROGRESS_ROOT/$USER_ROOT/$userId") {
                    basicAuth(username = username, password = password)
                }.body<List<Progress>>()
            } catch (exception: Exception) {
                throw exception
            }
        }
    }

    override suspend fun getSharedProgressListWithUserId(userId: Int): List<List<Progress>> {
        return withContext(Dispatchers.IO) {
            try {
                client.get("$PROGRESS_ROOT/$SHARED_ROOT/$userId") {
                    basicAuth(username = username, password = password)
                }.body<List<List<Progress>>>()
            } catch (exception: Exception) {
                throw exception
            }
        }
    }

    override suspend fun createProgress(progress: Progress) {
        client.runCatching {
            withContext(Dispatchers.IO) {
                post(PROGRESS_ROOT) {
                    setBody(progress)
                    contentType(ContentType.Application.Json)
                    basicAuth(username = username, password = password)
                }
            }
        }.onFailure {
            Log.d("exception", "error = ${it.message} of creating a new progress")
            throw it
        }.onSuccess {
            Log.d("progress", "Progress created successfully")
        }
    }

    override suspend fun updateProgress(id: Int, progress: Progress) {
        client.runCatching {
            withContext(Dispatchers.IO) {
                put("$PROGRESS_ROOT/$id") {
                    setBody(progress)
                    contentType(ContentType.Application.Json)
                    basicAuth(username = username, password = password)
                }
            }
        }.onFailure {
            Log.d("exception", "error = ${it.message} of updating progress")
            throw it
        }.onSuccess {
            Log.d("progress", "Progress updated successfully")
        }
    }

    override suspend fun deleteProgress(id: Int) {
        client.runCatching {
            withContext(Dispatchers.IO) {
                delete("$PROGRESS_ROOT/$id") {
                    basicAuth(username = username, password = password)
                }
            }
        }.onFailure {
            Log.d("exception", "error = ${it.message} of deleting a progress")
            throw it
        }.onSuccess {
            Log.d("progress", "Progress deleted successfully")
        }
    }

    override suspend fun shareProgress(sharedProgress: SharedProgress) {
        client.runCatching {
            withContext(Dispatchers.IO) {
                post("$PROGRESS_ROOT/$SHARE_ROOT") {
                    setBody(sharedProgress)
                    contentType(ContentType.Application.Json)
                    basicAuth(username = username, password = password)
                }
            }
        }.onFailure {
            Log.d("exception", "error = ${it.message} of sharing progress")
            throw it
        }.onSuccess {
            Log.d("progress", "Shared progress successfully")
        }
    }

    companion object {
        private var INSTANCE: ProgressRepository? = null
        fun getInstance(username: String, password: String): ProgressRepository {
            if (INSTANCE?.username != username || INSTANCE?.password != password || INSTANCE != null) {
                INSTANCE = ProgressRepository(username, password)
            }
            return INSTANCE!!
        }
    }
}