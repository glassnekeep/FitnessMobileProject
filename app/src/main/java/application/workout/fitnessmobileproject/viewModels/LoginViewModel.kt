package application.workout.fitnessmobileproject.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import application.workout.fitnessmobileproject.model.models.User
import application.workout.fitnessmobileproject.model.repository.UserRepository
import application.workout.fitnessmobileproject.utils.FitnessApplication
import application.workout.fitnessmobileproject.utils.USER
import application.workout.fitnessmobileproject.utils.exceptions.NotFoundServerApiException
import io.ktor.client.call.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(application: Application): AndroidViewModel(application) {

    private val _status = MutableLiveData<Boolean>()
    val status get() = _status


    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> get() = _username

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _password

    init {
        _user.value = USER
        _username.value = USER?.username
        _password.value = USER?.password
    }
    suspend fun doLogin(username: String, password: String): Boolean {
        /*getApplication<FitnessApplication>().apply {
            setCredentials(username, password)
            /*viewModelScope.launch {
                withContext(Dispatchers.IO) { validateUser() }
            }*/
        }
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.IO) {
                USER = try {
                    UserRepository.getInstance(getApplication(), username, password).getUserWithUsername(username).body<User>()
                }
                catch (e: NotFoundServerApiException) {
                    Log.d("error", "invalid input")
                    null
                }
            }
        }
        val deferred = viewModelScope.async(Dispatchers.IO) {
            USER = UserRepository.getInstance(getApplication(), username, password).getUserWithUsername(username).body<User>()
        }*/
        withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
            /*USER = try {
                UserRepository.getInstance(getApplication(), username, password)
                    .getUserWithUsername(username).body<User>()
            } catch (e: NotFoundServerApiException) {
                Log.d("error", "invalid input")
                null
            }
        }
        return if (USER != null) {
            getApplication<FitnessApplication>().setCredentials(username, password)
            true
        } else {
            false*/
            (getApplication() as FitnessApplication).let {
                it.setCredentials(username, password)
                it.validateUser()
            }
        }
        return USER != null
    }
}