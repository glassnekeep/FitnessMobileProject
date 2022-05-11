package application.workout.fitnessmobileproject.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import application.workout.fitnessmobileproject.model.models.Program
import application.workout.fitnessmobileproject.model.repository.repositories.ProgramRepository
import application.workout.fitnessmobileproject.utils.USER
import kotlinx.coroutines.launch
import kotlin.Exception

class ProgramsViewModel: ViewModel() {

    private var _programs = MutableLiveData<List<Program>>()

    val programs: LiveData<List<Program>> get() = _programs

    init {
        getPrograms()
    }

    private fun getPrograms() {
        viewModelScope.launch {
            try {
                _programs.value = ProgramRepository.getInstance(
                    username = USER?.username ?: "",
                    password = USER?.password ?: "")
                    .getAllPrograms()
            } catch (exception: Exception) {
                Log.d("exception", "error = ${exception.message} of getting all programs")
                throw exception
            }
        }
    }
}