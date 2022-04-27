package application.workout.fitnessmobileproject.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import application.workout.fitnessmobileproject.model.models.Settings
import application.workout.fitnessmobileproject.model.models.User
import application.workout.fitnessmobileproject.model.repository.SettingsRepository
import application.workout.fitnessmobileproject.utils.USER
import kotlinx.coroutines.launch
import java.lang.Exception

class SettingsViewModel: ViewModel() {

    private var _settings = MutableLiveData<Settings>()

    val settings get() = _settings

    private var _firstnameAndLastname = MutableLiveData<String>()

    val firstnameAndLastname get() = _firstnameAndLastname

    private var _phoneNumber = MutableLiveData<String>()

    val phoneNumber = _phoneNumber

    init {
        if (USER != null) {
            getCurrentSettings(USER!!.id)
        }
    }

    private fun getCurrentSettings(userId: Int) {
        viewModelScope.launch {
            try {
                _settings.value = SettingsRepository.getInstance(
                    username = USER?.username ?: "",
                    password = USER?.password ?: ""
                ).getSettingsWithUserId(userId)
            } catch (error: Throwable) {
                throw error
            }
        }
    }
}