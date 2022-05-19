package application.workout.fitnessmobileproject.utils

import android.app.Application
import application.workout.fitnessmobileproject.model.models.Program
import application.workout.fitnessmobileproject.model.models.Settings
import application.workout.fitnessmobileproject.model.models.User

var USER: User? = null
var SETTINGS: Settings? = null
var PROGRAMS: List<Program> = emptyList()
var USERNAME: String = ""
var PASSWORD: String = ""
const val baseServerUrl = "http://10.0.2.2:8080/"
const val baseResourceUrl = "static/"