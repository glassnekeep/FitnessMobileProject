package application.workout.fitnessmobileproject.model.repository

import android.app.Application
import android.content.res.Resources
import android.util.Log
import application.workout.fitnessmobileproject.model.KtorClientInstance
import application.workout.fitnessmobileproject.model.dao.UserDao
import application.workout.fitnessmobileproject.model.dao.UserDatabase
import application.workout.fitnessmobileproject.model.models.User
import application.workout.fitnessmobileproject.model.repository.routes.UserApi
import application.workout.fitnessmobileproject.utils.*
import application.workout.fitnessmobileproject.utils.exceptions.NotFoundServerApiException
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import java.net.HttpRetryException
import java.util.*

class UserRepository private constructor(val application: FitnessApplication, val username: String, val password: String) : UserApi {

    private val USER_ROOT = "http://10.0.2.2:8080/user"
    private val USERS_ROOT = "http://10.0.2.2:8080/users"

    //TODO тут нужно определить объект userDao и модели бд Room

    private val userDao: UserDao = UserDatabase.getDatabase(application).getUserDao()
    private var client: HttpClient = KtorClientInstance.getInstance(username, password)

    override suspend fun getUserWithId(id: Int): HttpResponse {
        val response = withContext(Dispatchers.IO) {
            client.get(USER_ROOT) {
                parameter("id", id)
                basicAuth(username = username, password = password)
            }
        }
        if (response.status.value in 300..500) {
            Log.d("exception", "300-499" + "username = $username, password = $password")
            throw NotFoundServerApiException("User not found")

        }
        return response
    }

    override suspend fun getUserWithEmail(email: String): HttpResponse {
        val response = withContext(Dispatchers.IO) {
            client.get(USER_ROOT) {
                parameter("email", email)
                basicAuth(username = username, password = password)
            }
        }
        if (response.status.value in 300..500) {
            Log.d("exception", "300-499" + "username = $username, password = $password")
            throw NotFoundServerApiException("User not found")
        }
        return response
    }

    override suspend fun getUserWithUsername(username: String): HttpResponse {
        val response = withContext(Dispatchers.IO) {
            client.get(USER_ROOT) {
                parameter("username", username)
                basicAuth(username = username, password = password)
            }
        }
        if (response.status.value in 300..500) {
            Log.d("exception", "300-499" + "username = $username, password = $password")
            throw NotFoundServerApiException("User not found")

        }
        return response
    }

    override suspend fun getUserWithPhoneNumber(phoneNumber: String): HttpResponse {
        val response = withContext(Dispatchers.IO) {
            client.get(USER_ROOT) {
                parameter("phoneNumber", phoneNumber)
                basicAuth(username = username, password = password)
            }
        }
        if (response.status.value in 300..500) {
            Log.d("exception", "300-499" + "username = $username, password = $password")
            throw NotFoundServerApiException("User not found")

        }
        return response
    }

    override suspend fun getAllUsers(): HttpResponse {
        val response = withContext(Dispatchers.IO) {
            client.get(USERS_ROOT)
        }
        if (response.status.value in 300..500) {
            Log.d("exception", "300-500")
            throw NotFoundServerApiException("No users found")
        }
        return response
    }

    override suspend fun getAllUsersId(): HttpResponse {
        val response = withContext(Dispatchers.IO) {
            client.get(USERS_ROOT)
        }
        if (response.status.value in 300..500) {
            Log.d("exception", "300-500")
            throw NotFoundServerApiException("No users found")
        }
        return response
    }

    override suspend fun createUser(user: User): HttpResponse {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(id: Int, user: User): HttpResponse {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUserWithId(id: Int): HttpResponse {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUserWithEmail(email: String): HttpResponse {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUserWithUsername(username: String): HttpResponse {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUserWithPhoneNumber(phoneNumber: String): HttpResponse {
        TODO("Not yet implemented")
    }

    companion object {
        private var INSTANCE: UserRepository? = null
        private var client: HttpClient? = null
        fun getInstance(application: Application, username: String, password: String): UserRepository {
            if (INSTANCE?.username != username || INSTANCE?.password != password || INSTANCE == null) {
                INSTANCE = UserRepository(application = application as FitnessApplication, username, password)
            }
            return INSTANCE!!
        }
    }
}