package application.workout.fitnessmobileproject.viewModels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import application.workout.fitnessmobileproject.model.models.User
import application.workout.fitnessmobileproject.model.repository.UserRepository
import application.workout.fitnessmobileproject.utils.LoadingState
import application.workout.fitnessmobileproject.utils.username
import io.ktor.client.call.*
import kotlinx.coroutines.launch

class SharedSplashViewModel(application: Application): AndroidViewModel(application) {
    private var _user = //MutableLiveData<User>()
        liveData<User> {
            val user = UserRepository.getInstance(application).getUserWithUsername(username)
            when(user.status.value) {
                in 200..299 -> {
                    emit(user.body())
                }
                in 400..499 -> {
                    Toast.makeText(application, "Client failed to get user", Toast.LENGTH_SHORT).show()
                    emit(User())
                }
                in 500..599 -> {
                    Toast.makeText(application, "Server failed to get user", Toast.LENGTH_SHORT).show()
                    emit(User())
                }
            }
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