package application.workout.fitnessmobileproject.model.repository

import android.app.Application
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import application.workout.fitnessmobileproject.model.KtorClientInstance
import application.workout.fitnessmobileproject.model.dao.UserDao
import application.workout.fitnessmobileproject.model.dao.UserDatabase
import application.workout.fitnessmobileproject.model.models.User
import application.workout.fitnessmobileproject.model.repository.routes.UserApi
import application.workout.fitnessmobileproject.utils.FitnessApplication
import application.workout.fitnessmobileproject.utils.password
import application.workout.fitnessmobileproject.utils.username
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.util.*

class UserRepository private constructor(application: Application) : UserApi {

    //TODO тут нужно определить объект userDao и модели бд Room

    private val userDao: UserDao = UserDatabase.getDatabase(application).getUserDao()
    private var client: HttpClient? = null

    fun createClientWithUsernameAndPassword(username: String, password: String) {
        client = KtorClientInstance.getInstance(username, password)
    }

    override suspend fun getUserWithId(id: Int): HttpResponse {
        val response = client?.get("http://10.0.2.2:8080/user") {
            parameter("id", id)
            basicAuth(username = username, password = password)
        }
        return response
    }

    override suspend fun getUserWithEmail(email: String): HttpResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getUserWithUsername(username: String): HttpResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getUserWithPhoneNumber(phoneNumber: String): HttpResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getAllUsers(): HttpResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getAllUsersId(): HttpResponse {
        TODO("Not yet implemented")
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
        fun getInstance(application: Application): UserRepository = INSTANCE ?: kotlin.run {
            INSTANCE = UserRepository(application = application)
            INSTANCE!!
        }
    }
}