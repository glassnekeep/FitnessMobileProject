package application.workout.fitnessmobileproject.utils

import android.app.Application
import application.workout.fitnessmobileproject.model.models.Program
import application.workout.fitnessmobileproject.model.models.Settings
import application.workout.fitnessmobileproject.model.models.User

var USER: User? = //User(1, "pizdec", "", "", "", "", "", "",23,23)
    null
var SETTINGS: Settings? = null
var PROGRAMS: List<Program> = emptyList()
var USERNAME: String = ""
var PASSWORD: String = ""