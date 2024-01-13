package com.example.data.api

import com.example.data.utils.TestApiClient
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.test.assertTrue

class CatApiTest {
    @Test
    fun getDataSuccessfully() = runBlocking {
        val catApi = CatApi(TestApiClient.httpClient)
        assertEquals(
            20,
            catApi.getCats().size
        )
    }

    @Test
    fun getDataWithError() = runBlocking {
        var isErrorHappened = false
        try {
            val catApi = CatApi(TestApiClient.httpClientWithError)
            catApi.getCats()
        } catch (e : Exception){
            isErrorHappened = true
        }
        assertTrue {
            isErrorHappened
        }
    }
}