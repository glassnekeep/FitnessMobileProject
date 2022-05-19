package application.workout.fitnessmobileproject.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import application.workout.fitnessmobileproject.model.models.Exercise
import application.workout.fitnessmobileproject.model.models.Program
import application.workout.fitnessmobileproject.model.models.Progress
import application.workout.fitnessmobileproject.model.repository.repositories.ProgramRepository
import application.workout.fitnessmobileproject.model.repository.repositories.ProgressRepository
import application.workout.fitnessmobileproject.utils.USER
import kotlinx.coroutines.launch

class ProgramViewModel: ViewModel() {

    private val _program = MutableLiveData<Program>()

    val program: LiveData<Program> get() = _program

    private var current = 0

    private var _currentExercise = MutableLiveData<Exercise>()

    val currentExercise: LiveData<Exercise> get() = _currentExercise

    private var _isLastExercise = MutableLiveData<Boolean>(false)

    val isLastExercise: LiveData<Boolean> get() = _isLastExercise

    /*init {
        initProgram(id)
    }*/

    /*fun getCurrentImage(): String {
        return current.value?.let { program.value?.exercise?.get(it)?.image } ?: ""
    }*/

    private fun getCurrentExercise() : Exercise? {
        return current.let { program.value?.exercise?.get(it) }
    }

    fun increaseCurrent() {
        if (current < getProgramSize() - 1) {
            current += 1
            program.value?.exercise?.get(current).also { _currentExercise.value = it }
            _isLastExercise.value = (current == getProgramSize() - 1)
        }
    }

    fun finishProgram() {
        current = 0
        _isLastExercise.value = (current == getProgramSize() - 1)
        addProgressToProgram(programId = program.value?.id ?: 0, userId = USER?.id ?: 0)
    }

    private fun getProgramSize(): Int {
        return program.value?.exercise?.size ?: 0
    }

    fun initProgram(id: Int) {
        viewModelScope.launch {
            try {
                _program.value = ProgramRepository.getInstance(
                    username = USER?.username ?: "",
                    password = USER?.password ?: ""
                ).getProgramWithId(id)
                _currentExercise.value = program.value!!.exercise[current]
            } catch (exception: Exception) {
                Log.d("exception", "error = ${exception.message} of getting a program")
            }
        }
    }

    private fun addProgressToProgram(programId: Int, userId: Int) {
        viewModelScope.launch {
            ProgressRepository.getInstance(
                username = USER?.username ?: "",
                password = USER?.password ?: ""
            ).let {
                val progress = it.getProgressWithUserAndProgram(userId = userId, programId = programId)
                if (progress.currentExercise < progress.program.numberOfExercises) {
                    it.updateProgress(progress.id, Progress(0, progress.program, progress.user, progress.currentExercise + 1))
                }
            }
        }
    }
}