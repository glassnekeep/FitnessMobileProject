package application.workout.fitnessmobileproject.model.repository

import android.util.Log
import application.workout.fitnessmobileproject.model.KtorClientInstance
import application.workout.fitnessmobileproject.model.models.Exercise
import application.workout.fitnessmobileproject.model.repository.routes.ExerciseApi
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class ExerciseRepository private constructor(val username: String, val password: String): ExerciseApi {
    private val EXERCISE_ROOT = "http://10.0.2.2:8080/exercise"
    private val client = KtorClientInstance.getInstance(username, password)
    override suspend fun getExerciseWithId(id: Int): Exercise {
        /*val response = withContext(Dispatchers.IO) {
            client.get(EXERCISE_ROOT) {

            }
        }*/
        return withContext(Dispatchers.IO) {
            try {
                client.get(EXERCISE_ROOT) {
                    parameter("id", id)
                    basicAuth(username = username, password = password)
                }.body<Exercise>()
            } catch (exception: Exception) {
                throw exception
            }
        }
    }

    override suspend fun getExerciseListWithProgramId(id: Int): List<Exercise> {
        return withContext(Dispatchers.IO) {
            try {
                client.get(EXERCISE_ROOT) {
                    parameter("program", id)
                    basicAuth(username = username, password = password)
                }.body<List<Exercise>>()
            } catch(exception: Exception) {
                throw exception
            }
        }
    }

    override suspend fun createExercise(exercise: Exercise)/*: HttpResponse*/ {
        client.runCatching {
            withContext(Dispatchers.IO) {
                post(EXERCISE_ROOT) {
                    setBody(exercise)
                }
            }
        }.onFailure {
            Log.d("exception", "error = ${it.message} of creating an exercise")
            throw it
        }.onSuccess {
            Log.d("exercise", "Exercise created successfully")
        }
    }

    override suspend fun updateExercise(id: Int, exercise: Exercise)/*: HttpResponse*/ {
        client.runCatching {
            withContext(Dispatchers.IO) {
                put("$EXERCISE_ROOT/$id") {
                    setBody(exercise)
                }
            }
        }.onFailure {
            Log.d("exception", "error = ${it.message} of updating an exercise")
            throw it
        }.onSuccess {
            Log.d("exercise", "Exercise updated successfully")
        }
    }

    override suspend fun deleteExercise(id: Int)/*: HttpResponse*/ {
        client.runCatching {
            withContext(Dispatchers.IO) {
                delete("$EXERCISE_ROOT/$id")
            }
        }.onFailure {
            Log.d("exception", "error = ${it.message} of deleting an exercise")
            throw it
        }.onSuccess {
            Log.d("exercise", "Exercise deleted successfully")
        }
    }
}