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

class ProgramViewModel(): ViewModel() {

    private val _program = MutableLiveData<Program>()

    val program: LiveData<Program> get() = _program

    var current = 0

    /*init {
        initProgram(id)
    }*/

    fun initProgram(id: Int) {
        viewModelScope.launch {
            try {
                _program.value = ProgramRepository.getInstance(
                    username = USER?.username ?: "",
                    password = USER?.password ?: ""
                ).getProgramWithId(id)
            } catch (exception: Exception) {
                Log.d("exception", "error = ${exception.message} of getting a program")
            }
        }
    }
}