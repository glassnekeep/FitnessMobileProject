package application.workout.fitnessmobileproject.model.repository.routes

import application.workout.fitnessmobileproject.model.models.Settings
import io.ktor.client.statement.*

interface SettingsApi {
    suspend fun getSettingsWithId(id: Int) : Settings
    suspend fun getSettingsWithUserId(userId: Int) : Settings
    suspend fun createSettings(settings: Settings) : HttpResponse
    suspend fun updateSettings(id: Int, settings: Settings) : HttpResponse
    suspend fun deleteSettingsWithId(id: Int) : HttpResponse
    suspend fun deleteSettingsWithUserId(userId: Int) : HttpResponse
}