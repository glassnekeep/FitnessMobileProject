package application.workout.fitnessmobileproject.utils.exceptions

import java.lang.Exception

class NotFoundServerApiException(message: String): Exception(message)

class BadRequestServerApiException(message: String): Exception(message)

class EmptyFieldException(message: String): Exception(message)