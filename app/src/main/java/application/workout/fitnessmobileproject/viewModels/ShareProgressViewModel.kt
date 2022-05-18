package application.workout.fitnessmobileproject.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import application.workout.fitnessmobileproject.model.models.Progress
import application.workout.fitnessmobileproject.model.models.SharedProgress
import application.workout.fitnessmobileproject.model.repository.repositories.ProgressRepository
import application.workout.fitnessmobileproject.utils.USER
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.util.*

class ShareProgressViewModel: ViewModel() {

    private var _sharedProgressList = MutableLiveData<List<List<Progress>>>()

    val sharedProgressList get() = _sharedProgressList

    /*Не совсем уверен в таком подходе, нашел на стековерфлоу, стоит потом будет еще подумать
    может стоит как-то по-другому реализовать отлов ошибок из вью модели
    возможно, стоит так же пробрасывать исключения дальше во фрагмент
     */
    private var _errorMessage = MutableLiveData<String>()

    val errorMessage get() = _errorMessage

    private var username: String

    private var password: String

    private var senderId: Int

    init {
        //Стоит отрефакторить если будет время
        if (USER != null) {
            getSharedProgressList(USER!!.id, USER!!.username, USER!!.password)
        }
        username = USER?.username ?: ""
        password = USER?.password ?: ""
        senderId = USER?.id ?: 0

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

    fun shareProgress(userId: Int, time: Date) {
        viewModelScope.launch {
            try {
                ProgressRepository.getInstance(
                    username = username,
                    password = password
                ).shareProgress(SharedProgress(senderId = senderId, recipientId = userId, time = ))
            }
        }
    }
}