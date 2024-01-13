package com.example.data.api

import com.example.data.api.model.CatApiModel
import com.example.data.api.model.CatResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

private const val CATS_API_URL =
    "https://api.flickr.com/services/feeds/photos_public.gne?format=json&tags=cat&nojsoncallback=1"

class CatApi(private val httpClient: HttpClient) {
    suspend fun getCats(): List<CatApiModel> {
        return httpClient.get(CATS_API_URL).body<CatResponse>().items
    }
}
