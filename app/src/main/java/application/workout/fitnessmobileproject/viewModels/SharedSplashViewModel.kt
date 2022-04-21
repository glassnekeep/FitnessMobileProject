package application.workout.fitnessmobileproject.viewModels

import android.app.Application
import androidx.lifecycle.*
import application.workout.fitnessmobileproject.model.models.User
import application.workout.fitnessmobileproject.model.repository.UserRepository
import application.workout.fitnessmobileproject.utils.LoadingState
import kotlinx.coroutines.launch

class SharedSplashViewModel(application: Application): AndroidViewModel(application) {
    private var _user = MutableLiveData<User>()

    val user: LiveData<User>
        get() = _user

    private val _status = MutableLiveData<LoadingState>()

    val status
        get() = _status

    private fun getCurrentUser(username: String, password: String) {
        viewModelScope.launch {
            _status.value = LoadingState.LOADING
            try {
                _user.value = UserRepository.getInstance(getApplication()).getUser(23)
            }
        }
    }
}