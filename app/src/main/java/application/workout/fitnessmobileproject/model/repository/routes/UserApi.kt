package application.workout.fitnessmobileproject.model.repository.routes

import application.workout.fitnessmobileproject.model.models.User
import io.ktor.client.statement.*

interface UserApi {
    //TODO Возможно стоит сделать так чтобы все функции возвращали HttpResponse, даже get, потому что так можно будет получать код ошибки если что, а json можно через body() получать
//    suspend fun getUserWithId(id: Int) : User
//    suspend fun getUserWithEmail(email: String) : User
//    suspend fun getUserWithUsername(username: String) : User
//    suspend fun getUserWithPhoneNumber(phoneNumber: String) : User
//    suspend fun getAllUsers() : List<User>
//    suspend fun getAllUsersId() : List<Int>
    suspend fun getUserWithId(id: Int) : HttpResponse?
    suspend fun getUserWithEmail(email: String) : HttpResponse?
    suspend fun getUserWithUsername(username: String) : HttpResponse?
    suspend fun getUserWithPhoneNumber(phoneNumber: String) : HttpResponse?
    suspend fun getAllUsers() : HttpResponse?
    suspend fun getAllUsersId() : HttpResponse?
    suspend fun createUser(user: User) : HttpResponse?
    suspend fun updateUser(id: Int, user: User) : HttpResponse?
    suspend fun deleteUserWithId(id: Int) : HttpResponse?
    suspend fun deleteUserWithEmail(email: String) : HttpResponse?
    suspend fun deleteUserWithUsername(username: String) : HttpResponse?
    suspend fun deleteUserWithPhoneNumber(phoneNumber: String) : HttpResponse?
}