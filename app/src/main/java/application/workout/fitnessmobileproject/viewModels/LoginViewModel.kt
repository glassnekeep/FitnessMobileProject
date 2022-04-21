package application.workout.fitnessmobileproject.viewModels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import application.workout.fitnessmobileproject.model.models.User
import application.workout.fitnessmobileproject.utils.FitnessApplication
import application.workout.fitnessmobileproject.utils.USER

class LoginViewModel(application: Application): AndroidViewModel(application) {
    private val _username = MutableLiveData<String>()
    val username get() = _username
    private val _password = MutableLiveData<String>()
    val password get() = _password
    init {
        _username.value = USER?.username
        _password.value = USER?.password
    }
    fun doLogin(username: String, password: String) {
        getApplication<FitnessApplication>().setCredentials(username, password)
    }
}