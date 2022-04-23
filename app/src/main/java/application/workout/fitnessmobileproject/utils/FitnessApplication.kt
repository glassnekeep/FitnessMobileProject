package application.workout.fitnessmobileproject.utils

import android.app.Application
import android.util.Log
import application.workout.fitnessmobileproject.model.models.*
import application.workout.fitnessmobileproject.model.repository.UserRepository
import application.workout.fitnessmobileproject.utils.exceptions.NotFoundServerApiException
import io.ktor.client.call.*
import kotlinx.coroutines.*

class FitnessApplication: Application() {

    private val USERNAME = "username"
    private val PASSWORD = "password"
    var user: User? = null

    fun setCredentials(username: String, password: String) {
        val preferences = applicationContext.getSharedPreferences("login_info", MODE_PRIVATE)
        preferences.edit().let {
            it.putString(USERNAME, username)
            it.putString(PASSWORD, password)
            it.apply()
        }
    }
    fun getUsername(): String? {
        val preferences = applicationContext.getSharedPreferences("login_info", MODE_PRIVATE)
        return preferences.getString(USERNAME, "")
    }
    fun getPassword(): String? {
        val preferences = applicationContext.getSharedPreferences("login_info", MODE_PRIVATE)
        return preferences.getString(PASSWORD, "")
    }
    suspend fun validateUser() {
        setCredentials("test", "password")
        val username = getUsername() ?: ""
        USER = try {
            UserRepository.getInstance(this, getUsername()!!, getPassword()!!).getUserWithUsername(username).body()
        } catch (e: NotFoundServerApiException) {
            null
        }
    }
    fun checkUser(): Boolean {
        return user == null
    }
    companion object {
        var user: User? = null
        var programs: List<Program> = emptyList()
        var settings: Settings? = null
        var calendars: List<Calendar> = emptyList()
        var progresses: List<Progress> = emptyList()
        var exercises: List<Exercise> = emptyList()
    }
}