package application.workout.fitnessmobileproject.utils

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import application.workout.fitnessmobileproject.model.models.*

class FitnessApplication: Application() {

    companion object {
        var user: User? = null
        var programs: List<Program> = emptyList()
        var settings: Settings? = null
        var calendars: List<Calendar> = emptyList()
        var progresses: List<Progress> = emptyList()
        var exercises: List<Exercise> = emptyList()
    }
}