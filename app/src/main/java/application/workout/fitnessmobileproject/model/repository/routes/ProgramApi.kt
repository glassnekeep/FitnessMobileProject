package application.workout.fitnessmobileproject.model.repository.routes

import application.workout.fitnessmobileproject.model.models.Program
import application.workout.fitnessmobileproject.model.models.User
import io.ktor.client.statement.*

interface ProgramApi {
//    suspend fun getProgramWithId(id: Int) : Program
//    suspend fun getProgramListWithUser(id: Int) : List<Program>
//    suspend fun getUserListWithProgram(id: Int) : List<User>
//    suspend fun getAllPrograms() : List<Program>
    suspend fun getProgramWithId(id: Int) : HttpResponse
    suspend fun getProgramListWithUser(id: Int) : HttpResponse
    suspend fun getUserListWithProgram(id: Int) : HttpResponse
    suspend fun getAllPrograms() : HttpResponse
    suspend fun addUserToProgram(userId: Int, programId: Int) //: HttpResponse
    suspend fun addExerciseToProgram(exerciseId: Int, programId: Int) //: HttpResponse
    suspend fun createProgram(program: Program) //: HttpResponse
    suspend fun updateProgram(id: Int, program: Program) //: HttpResponse
    suspend fun deleteProgram(programId: Int) //: HttpResponse
    //TODO здесь и в серверной части нужно добавить удаление пользователя из программы и программы из пользователя
    suspend fun deleteExerciseFromProgram(exerciseId: Int, programId: Int)// : HttpResponse
    suspend fun deleteUserFromProgram(userId: Int, programId: Int)// : HttpResponse
}