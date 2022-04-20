package application.workout.fitnessmobileproject.model.repository

import android.app.Application
import application.workout.fitnessmobileproject.model.KtorClientInstance
import application.workout.fitnessmobileproject.model.dao.UserDao
import application.workout.fitnessmobileproject.model.dao.UserDatabase

class UserRepository private constructor(application: Application) {

    //TODO тут нужно определить объект userDao и модели бд Room

    private val userDao: UserDao = UserDatabase.getDatabase(application).getUserDao()
    private val client = KtorClientInstance.getInstance()

    companion object {
        private var INSTANCE: UserRepository? = null
        fun getInstance(application: Application): UserRepository = INSTANCE ?: kotlin.run {
            INSTANCE = UserRepository(application = application)
            INSTANCE!!
        }
    }
}