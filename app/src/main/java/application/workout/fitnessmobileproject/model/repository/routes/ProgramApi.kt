package application.workout.fitnessmobileproject.model.repository.routes

import application.workout.fitnessmobileproject.model.models.Program
import application.workout.fitnessmobileproject.model.models.User
import io.ktor.client.statement.*

interface ProgramApi {
    suspend fun getProgramWithId(id: Int) : Program
    suspend fun getProgramListWithUser(id: Int) : List<Program>
    suspend fun getUserListWithProgram(id: Int) : List<User>
    suspend fun getAllPrograms() : List<Program>
    suspend fun addUserToProgram(userId: Int, programId: Int) : HttpResponse
    suspend fun addProgramToUser(programId: Int, userId: Int) : HttpResponse
    suspend fun createProgram(program: Program) : HttpResponse
    suspend fun updateProgram(program: Program) : HttpResponse
    suspend fun deleteProgram(programId: Int) : HttpResponse
    //TODO здесь и в серверной части нужно добавить удаление пользователя из программы и программы из пользователя
}