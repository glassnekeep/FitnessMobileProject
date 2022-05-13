package application.workout.fitnessmobileproject.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import application.workout.fitnessmobileproject.model.models.Progress
import application.workout.fitnessmobileproject.model.models.User
import application.workout.fitnessmobileproject.model.repository.repositories.ProgressRepository
import application.workout.fitnessmobileproject.utils.USER
import kotlinx.coroutines.launch

class StatisticsViewModel: ViewModel() {

    private var _userProgress = MutableLiveData<List<Progress>>()

    val userProgress: LiveData<List<Progress>> get() = _userProgress

    init {
        if (USER != null) {
            getProgressListWithUser(USER!!.id, USER!!.username, USER!!.password)
        }
    }

    private fun getProgressListWithUser(userId: Int, username: String, password: String) {
        viewModelScope.launch {
            try {
                _userProgress.value = ProgressRepository.getInstance(
                    username = username,
                    password = password
                ).getProgressListWithUserId(userId)
            } catch (exception: Exception) {
                Log.d("exception", "error = ${exception.message} of getting list of progresses in statistics view model")
            }
        }
    }
}