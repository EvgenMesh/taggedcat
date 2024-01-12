package com.example.data.repository

import com.example.data.api.ApiClient
import com.example.data.api.CatApi
import com.example.data.utils.TestApiClient
import com.example.domain.entities.Cat
import com.example.domain.entities.Result
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertTrue

class CatRepositoryImplTest {
    @Test
    fun getDataSuccessfully() = runBlocking {
        val catRepositoryImpl = CatRepositoryImpl(CatApi(TestApiClient.httpClient))
        assertEquals(
            20,
            (catRepositoryImpl.getData() as Result.Success<List<Cat>>).value.size
        )
    }

    @Test
    fun getDataWithError() = runBlocking {
        val catRepositoryImpl = CatRepositoryImpl(CatApi(TestApiClient.httpClientWithError))
        assertTrue {
            catRepositoryImpl.getData() is Result.Failure
        }
    }
}