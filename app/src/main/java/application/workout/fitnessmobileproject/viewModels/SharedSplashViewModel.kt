package application.workout.fitnessmobileproject.viewModels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import application.workout.fitnessmobileproject.model.models.User
import application.workout.fitnessmobileproject.model.repository.UserRepository
import application.workout.fitnessmobileproject.utils.LoadingState
import application.workout.fitnessmobileproject.utils.USER
import application.workout.fitnessmobileproject.utils.USERNAME
import io.ktor.client.call.*
import kotlinx.coroutines.launch

class SharedSplashViewModel(application: Application): AndroidViewModel(application) {
    private var _user = MutableLiveData<User>()

    init {
        _user.value = USER
    }

    val user: LiveData<User>
        get() = _user

    private val _status = MutableLiveData<LoadingState>()

    val status
        get() = _status

    private fun getCurrentUser(username: String, password: String) {
        viewModelScope.launch {
            _status.value = LoadingState.LOADING
        }
    }
}