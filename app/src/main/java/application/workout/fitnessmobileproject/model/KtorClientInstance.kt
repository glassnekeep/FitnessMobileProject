package application.workout.fitnessmobileproject.model

import android.accounts.NetworkErrorException
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.cache.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.auth.*
import io.ktor.serialization.kotlinx.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.serializer

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
                install(HttpCache)
                install(Logging) {
                    logger = Logger.ANDROID
                }
                install(HttpTimeout) {
                    requestTimeoutMillis = 5000
                }
                expectSuccess = true
                install(HttpRequestRetry) {
                    maxRetries = 5
                    retryIf { request, response ->
                        !response.status.isSuccess()
                    }
                    retryOnExceptionIf { request, cause ->
                        cause is NetworkErrorException
                    }
                    retryOnServerErrors(maxRetries = maxRetries)
                }
            }
        }  else {
            client?.plugin(Auth)?.basic { BasicAuthCredentials(username = username, password = password) }
        }
        return client!!
    }
    fun close() = client?.close()
}