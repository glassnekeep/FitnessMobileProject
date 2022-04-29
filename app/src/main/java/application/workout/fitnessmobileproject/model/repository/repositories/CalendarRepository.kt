package application.workout.fitnessmobileproject.model.repository.repositories

import android.util.Log
import application.workout.fitnessmobileproject.model.KtorClientInstance
import application.workout.fitnessmobileproject.model.models.Calendar
import application.workout.fitnessmobileproject.model.repository.routes.CalendarApi
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CalendarRepository private constructor(val username: String, val password: String): CalendarApi {
    private val CALENDAR_ROOT = "http://10.0.2.2:8080/calendar"
    private val client = KtorClientInstance.getInstance(username, password)

    override suspend fun getCalendarWithId(id: Int): Calendar {
        return withContext(Dispatchers.IO) {
            try {
                client.get(CALENDAR_ROOT) {
                    parameter("id", id)
                    basicAuth(username = username, password = password)
                }.body<Calendar>()
            } catch (exception: Exception) {
                throw exception
            }
        }
    }

    override suspend fun getCalendarListWithUser(id: Int): List<Calendar> {
        return withContext(Dispatchers.IO) {
            try {
                client.get(CALENDAR_ROOT) {
                    parameter("user", id)
                    basicAuth(username = username, password = password)
                }.body<List<Calendar>>()
            } catch (exception: Exception) {
                throw exception
            }
        }
    }

    override suspend fun createCalendar(calendar: Calendar) {
        client.runCatching {
            withContext(Dispatchers.IO) {
                post(CALENDAR_ROOT) {
                    basicAuth(username = username, password = password)
                    setBody(calendar)
                }
            }
        }.onFailure {
            Log.d("exception", "error = ${it.message} of creating a calendar")
            throw it
        }.onSuccess {
            Log.d("calendar", "Calendar created successfully")
        }
    }

    override suspend fun updateCalendar(id: Int, calendar: Calendar) {
        client.runCatching {
            withContext(Dispatchers.IO) {
                put("$CALENDAR_ROOT/$id") {
                    setBody(calendar)
                    basicAuth(username = username, password = password)
                }
            }
        }.onFailure {
            Log.d("exception", "error = ${it.message} of updating a calendar")
            throw it
        }.onSuccess {
            Log.d("calendar", "Calendar updated successfully")
        }
    }

    override suspend fun deleteCalendar(id: Int) {
        client.runCatching {
            withContext(Dispatchers.IO) {
                delete("$CALENDAR_ROOT/$id") {
                    basicAuth(username = username, password = password)
                }
            }
        }.onFailure {
            Log.d("exception", "error ${it.message} of deleting a calendar")
            throw it
        }.onSuccess {
            Log.d("calendar", "Deleted calendar successfully")
        }
    }

    companion object {
        private var INSTANCE: CalendarRepository? = null
        fun getInstance(username: String, password: String): CalendarRepository {
            if (INSTANCE?.username != username || INSTANCE?.password != password || INSTANCE != null) {
                INSTANCE = CalendarRepository(username, password)
            }
            return INSTANCE!!
        }
    }

}