package application.workout.fitnessmobileproject.model.repository.routes

import androidx.lifecycle.LiveData
import application.workout.fitnessmobileproject.model.models.Calendar
import io.ktor.client.statement.*

interface CalendarApi {
    suspend fun getCalendarWithId(id: Int) : Calendar
    suspend fun getCalendarListWithUser(id: Int) : List<Calendar>
    suspend fun createCalendar(calendar: Calendar) : HttpResponse
    suspend fun updateCalendar(id: Int, calendar: Calendar) : HttpResponse
    suspend fun deleteCalendar(id: Int) : HttpResponse
}