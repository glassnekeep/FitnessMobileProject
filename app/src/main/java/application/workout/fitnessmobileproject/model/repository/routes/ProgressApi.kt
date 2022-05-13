package application.workout.fitnessmobileproject.model.repository.routes

import application.workout.fitnessmobileproject.model.models.Progress
import application.workout.fitnessmobileproject.model.models.SharedProgress
import io.ktor.client.statement.*

interface ProgressApi {
    //TODO Возможно стоит добавить сюда метод который будет получать прогресс по всем программам пользователя
//    suspend fun getProgressWithId(id: Int) : Progress
//    suspend fun getProgressWithUserAndProgram(userId: Int, programId: Int) : Progress
    suspend fun getProgressWithId(id: Int) : Progress//HttpResponse
    suspend fun getProgressWithUserAndProgram(userId: Int, programId: Int) : Progress//HttpResponse
    suspend fun getProgressListWithUserId(userId: Int) : List<Progress>
    suspend fun createProgress(progress: Progress) //: HttpResponse
    suspend fun updateProgress(id: Int, progress: Progress) //: HttpResponse
    suspend fun deleteProgress(id: Int) //: HttpResponse
    suspend fun shareProgress(sharedProgress: SharedProgress)
}