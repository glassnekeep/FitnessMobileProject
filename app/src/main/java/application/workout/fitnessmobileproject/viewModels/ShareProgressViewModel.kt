package application.workout.fitnessmobileproject.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import application.workout.fitnessmobileproject.model.models.Progress
import application.workout.fitnessmobileproject.model.repository.repositories.ProgressRepository
import application.workout.fitnessmobileproject.utils.USER
import kotlinx.coroutines.launch

class ShareProgressViewModel: ViewModel() {

    private var _sharedProgressList = MutableLiveData<List<List<Progress>>>()

    val sharedProgressList get() = _sharedProgressList

    init {
        if (USER != null) {
            getSharedProgressList(USER!!.id, USER!!.username, USER!!.password)
        }
    }

    private fun getSharedProgressList(userId: Int, username: String, password: String) {
        viewModelScope.launch {
            try {
                _sharedProgressList.value = ProgressRepository.getInstance(
                    username = username,
                    password = password
                ).getSharedProgressListWithUserId(userId)
            } catch (exception: Exception) {
                Log.d("exception", "error = ${exception.message} of getting shared progress list")
            }
        }
    }
}