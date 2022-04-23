package application.workout.fitnessmobileproject.model

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.auth.*
import io.ktor.serialization.kotlinx.json.*

object KtorClientInstance {
    private var client: HttpClient? = null
    fun getInstance(username: String, password: String): HttpClient {
        if (client == null) {
            client = HttpClient(Android) {
                install(ContentNegotiation) {
                    json()
                }
                install(Auth) {
                    basic {
                        credentials {
                            BasicAuthCredentials(username = username, password = password)
                        }
                        realm = "Access to major paths"
                    }
                }
            }
        }  else {
            client?.plugin(Auth)?.basic { BasicAuthCredentials(username, password) }
        }
        return client!!
    }
    fun close() = client?.close()
}