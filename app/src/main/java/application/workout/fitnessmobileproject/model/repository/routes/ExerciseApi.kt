package application.workout.fitnessmobileproject.model.repository.routes

import application.workout.fitnessmobileproject.model.models.Exercise
import io.ktor.client.statement.*

interface ExerciseApi {
    /*suspend fun getExerciseWithId(id: Int) : Exercise
    suspend fun getExerciseListWithProgramId(id: Int) : List<Exercise>*/
    suspend fun getExerciseWithId(id: Int): HttpResponse
    suspend fun getExerciseListWithProgramId(id: Int) : HttpResponse
    suspend fun createExercise(exercise: Exercise) : HttpResponse
    suspend fun updateExercise(id: Int, exercise: Exercise) : HttpResponse
    suspend fun deleteExercise(id: Int) : HttpResponse
}