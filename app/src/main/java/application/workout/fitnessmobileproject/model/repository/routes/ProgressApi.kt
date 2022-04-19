package application.workout.fitnessmobileproject.model.repository.routes

import application.workout.fitnessmobileproject.model.models.Progress
import io.ktor.client.statement.*

interface ProgressApi {
//    suspend fun getProgressWithId(id: Int) : Progress
//    suspend fun getProgressWithUserAndProgram(userId: Int, programId: Int) : Progress
    suspend fun getProgressWithId(id: Int) : HttpResponse
    suspend fun getProgressWithUserAndProgram(userId: Int, programId: Int) : HttpResponse
    suspend fun createProgress(progress: Progress) : HttpResponse
    suspend fun updateProgress(id: Int, progress: Progress) : HttpResponse
    suspend fun deleteProgress(id: Int) : HttpResponse
}