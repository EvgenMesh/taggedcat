package com.example.data.utils

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.ByteReadChannel
import kotlinx.serialization.json.Json

object TestApiClient {
    val httpClient = HttpClient(MockEngine { request ->
        if (request.url.encodedPathAndQuery == "/services/feeds/photos_public.gne?format=json&tags=cat&nojsoncallback=1") {
            respond(
                content = ByteReadChannel(ClassLoader.getSystemResource("cats.json").readText()),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        } else {
            respond(
                content = ByteReadChannel("Not implementing"),
                status = HttpStatusCode.InternalServerError,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
    })
    {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                }
            )
        }
    }
    val httpClientWithError = HttpClient(MockEngine {
        respond(
            content = ByteReadChannel("Not implementing"),
            status = HttpStatusCode.InternalServerError,
            headers = headersOf(HttpHeaders.ContentType, "application/json")
        )
    })
}