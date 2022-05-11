package application.workout.fitnessmobileproject.model.repository.repositories

import android.util.Log
import application.workout.fitnessmobileproject.model.KtorClientInstance
import application.workout.fitnessmobileproject.model.models.Program
import application.workout.fitnessmobileproject.model.models.User
import application.workout.fitnessmobileproject.model.repository.routes.ProgramApi
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class ProgramRepository private constructor(val username: String, val password: String): ProgramApi {
    private val PROGRAM_ROOT = "http://10.0.2.2:8080/program"
    private val PROGRAMS_ROOT = "http://10.0.2.2:8080/programs"
    private val USER_ROOT = "user"
    private val USERS_ROOT = "users"
    private val NEW_ROOT = "new"
    private val client: HttpClient = KtorClientInstance.getInstance(username, password)
    override suspend fun getProgramWithId(id: Int): Program/*HttpResponse*/ {
        /*val response = withContext(Dispatchers.IO) {
            client.get("$PROGRAM_ROOT/${id.toString()}") {
                basicAuth(username = username, password = password)
            }
        }
        if (response.status.value in 300..600) {

        }*/
        return withContext(Dispatchers.IO) {
            try {
                client.get("$PROGRAM_ROOT/${id.toString()}") {
                    basicAuth(username = username, password = password)
                    expectSuccess = true
                }.body<Program>()
            } catch(exception: Exception) {
                Log.d("exception", "$exception of getting program with id")
                throw exception
            }
        }
    }

    override suspend fun getProgramListWithUser(id: Int): List<Program>/*HttpResponse*/ {
        return withContext(Dispatchers.IO) {
            try {
                client.get("$PROGRAM_ROOT/$USER_ROOT/${id.toString()}") {
                    basicAuth(username = username, password = password)
                    expectSuccess = true
                }.body<List<Program>>()
            } catch(exception: Exception) {
                Log.d("exception", "$exception of getting program list with user id")
                throw exception
            }
        }
    }

    override suspend fun getUserListWithProgram(id: Int): List<User>/*HttpResponse*/ {
        return withContext(Dispatchers.IO) {
            try {
                client.get("$PROGRAM_ROOT/$USERS_ROOT/$id") {
                    basicAuth(username = username, password = password)
                    expectSuccess = true
                }.body<List<User>>()
            } catch (exception: Exception) {
                Log.d("exception", "$exception of getting user list with program")
                throw exception
            }
        }
    }

    override suspend fun getAllPrograms(): List<Program>/*HttpResponse*/ {
        return withContext(Dispatchers.IO) {
            try {
                client.get(PROGRAMS_ROOT) {
                    basicAuth(username = username, password = password)
                    expectSuccess = true
                }.body<List<Program>>()
            } catch (exception: Exception) {
                Log.d("exception", "$exception of getting program list")
                throw exception
            }
        }
    }

    override suspend fun addUserToProgram(userId: Int, programId: Int) {
        client.runCatching {
            withContext(Dispatchers.IO) {
                post("$NEW_ROOT/$programId") {
                    parameter("user", userId)
                    basicAuth(username = username, password = password)
                    //expectSuccess = true
                }
            }
        }.onFailure {
            Log.d("exception", "error = ${it.message} of adding user to program")
            throw it
        }.onSuccess {
            Log.d("program", "Added user to program successfully")
        }
    }

    override suspend fun addExerciseToProgram(exerciseId: Int, programId: Int) {
        client.runCatching {
            withContext(Dispatchers.IO) {
                post("$NEW_ROOT/$programId") {
                    parameter("exercise", exerciseId)
                    basicAuth(username = username, password = password)
                    //expectSuccess = true
                }
            }
        }.onFailure {
            Log.d("exception", "error = ${it.message} of adding an exercise to program")
            throw it
        }.onSuccess {
            Log.d("program", "Added exercise to program")
        }
    }

    override suspend fun createProgram(program: Program) {
        client.runCatching {
            withContext(Dispatchers.IO) {
                post(PROGRAM_ROOT) {
                    setBody(program)
                    basicAuth(username = username, password = password)
                }
            }
        }.onFailure {
            Log.d("exception", "error = ${it.message} of creating a program")
            throw it
        }.onSuccess {
            Log.d("program","Program created successfully")
        }
    }

    override suspend fun updateProgram(id: Int, program: Program) {
        client.runCatching {
            withContext(Dispatchers.IO) {
                put("$PROGRAM_ROOT/$id") {
                    setBody(program)
                    basicAuth(username = username, password = password)
                }
            }
        }.onFailure {
            Log.d("exception", "error = ${it.message} of updating a program")
            throw it
        }.onSuccess {
            Log.d("program","Program updated successfully")
        }
    }

    override suspend fun deleteProgram(programId: Int) {
        client.runCatching {
            withContext(Dispatchers.IO) {
                delete("${PROGRAM_ROOT}/$programId") {
                    basicAuth(username = username, password = password)
                }
            }
        }.onFailure {
            Log.d("exception", "error = ${it.message} of deleting a program")
            throw it
        }.onSuccess {
            Log.d("program","Program deleted successfully")
        }
    }

    override suspend fun deleteExerciseFromProgram(exerciseId: Int, programId: Int) {
        client.runCatching {
            withContext(Dispatchers.IO) {
                delete("$PROGRAM_ROOT/$programId") {
                    parameter("exercise", exerciseId)
                    basicAuth(username = username, password = password)
                }
            }
        }.onFailure {
            Log.d("exception", "error = ${it.message} of deleting exercise from program")
            throw it
        }.onSuccess {
            Log.d("program", "Successfully deleted exercise from program")
        }
    }

    override suspend fun deleteUserFromProgram(userId: Int, programId: Int) {
        client.runCatching {
            withContext(Dispatchers.IO) {
                delete("$PROGRAM_ROOT/$programId") {
                    parameter("user", userId)
                    basicAuth(username = username, password = password)
                }
            }
        }.onFailure {
            Log.d("exception", "error = ${it.message} of deleting user from program")
            throw it
        }.onSuccess {
            Log.d("program", "Successfully deleted user from program")
        }
    }

    companion object {
        private var INSTANCE: ProgramRepository? = null
        fun getInstance(username: String, password: String): ProgramRepository {
            if (INSTANCE?.username != username || INSTANCE?.password != password || INSTANCE == null) {
                INSTANCE = ProgramRepository(username, password)
            }
            return INSTANCE!!
        }
    }
}