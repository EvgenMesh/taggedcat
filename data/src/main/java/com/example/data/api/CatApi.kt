package com.example.data.api

import com.example.data.mapper.mapToCatList
import com.example.data.model.CatResponse
import com.example.domain.entities.Cat
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class CatApi(private val httpClient: HttpClient) {
    suspend fun getCats(): List<Cat> {
        return httpClient.use {
            it.get("https://api.flickr.com/services/feeds/photos_public.gne?format=json&tags=cat&nojsoncallback=1")
                .body<CatResponse>().items.mapToCatList()
        }
    }

}