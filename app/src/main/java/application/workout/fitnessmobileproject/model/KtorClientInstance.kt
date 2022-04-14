package application.workout.fitnessmobileproject.model

import io.ktor.client.*
import io.ktor.client.engine.android.*

object KtorClientInstance {
    private var client: HttpClient? = null
    fun getInstance() = client ?: kotlin.run {client = HttpClient(Android) }
    fun close() = client?.close()
}