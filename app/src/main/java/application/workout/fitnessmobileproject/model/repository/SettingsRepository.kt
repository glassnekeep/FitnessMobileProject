package application.workout.fitnessmobileproject.model.repository

import android.util.Log
import application.workout.fitnessmobileproject.model.KtorClientInstance
import application.workout.fitnessmobileproject.model.models.Settings
import application.workout.fitnessmobileproject.model.repository.routes.SettingsApi
import application.workout.fitnessmobileproject.utils.exceptions.NotFoundServerApiException
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SettingsRepository private constructor(val username: String, val password: String): SettingsApi {
    private val SETTINGS_ROOT = "http://10.0.2.2:8080/settings"
    private val client: HttpClient = KtorClientInstance.getInstance(username, password)
    override suspend fun getSettingsWithId(id: Int): Settings {
        val response = withContext(Dispatchers.IO) {
            client.get(SETTINGS_ROOT) {
                parameter("id", id)
                basicAuth(username = username, password = password)
            }
        }
        if (response.status.value in 300..600) {
            Log.d("exception", "error = ${response.status.value} of getting settings via id")
            throw NotFoundServerApiException("Settings not found")
        }
        return response.body()
    }

    override suspend fun getSettingsWithUserId(userId: Int): Settings {
        val response = withContext(Dispatchers.IO) {
            client.get(SETTINGS_ROOT) {
                parameter("user", userId)
                basicAuth(username = username, password = password)
            }
        }
        if (response.status.value in 300..600) {
            Log.d("exception", "error = ${response.status.value} of getting settings with user id")
            throw NotFoundServerApiException("Settings not found")
        }
        return response.body()
    }

    override suspend fun createSettings(settings: Settings) {
        client.runCatching {
            post(SETTINGS_ROOT) {
                setBody(settings)
            }
        }.onFailure {
            Log.d("exception", "error = ${it.message} of posting settings")
            throw it
        }.onSuccess {
            Log.d("settings", "Posted settings successfully")
        }
    }

    override suspend fun updateSettings(id: Int, settings: Settings) {
        client.runCatching {
            post(SETTINGS_ROOT) {
                parameter("id", id)
                setBody(settings)
            }
        }.onFailure {
            Log.d("exception", "error = ${it.message} of updating settings")
            throw it
        }.onSuccess {
            Log.d("settings", "Updated settings successfully")
        }
    }

    override suspend fun deleteSettingsWithId(id: Int) {
        client.runCatching {
            delete(SETTINGS_ROOT) {
                parameter("id", id)
            }
        }.onFailure {
            Log.d("exception", "error = ${it.message} of deleting settings")
            throw it
        }.onSuccess {
            Log.d("settings", "Deleted settings successfully")
        }
    }

    override suspend fun deleteSettingsWithUserId(userId: Int) {
        client.runCatching {
            delete(SETTINGS_ROOT) {
                parameter("user", userId)
            }
        }.onFailure {
            Log.d("exception", "error = ${it.message} of deleting settings")
            throw it
        }.onSuccess {
            Log.d("settings", "Deleted settings with user successfully")
        }
    }

    companion object {
        private var INSTANCE: SettingsRepository? = null
        fun getInstance(username: String, password: String): SettingsRepository {
            if (INSTANCE?.username != username || INSTANCE?.password != password || INSTANCE == null) {
                INSTANCE = SettingsRepository(username, password)
            }
            return INSTANCE!!
        }
    }
}