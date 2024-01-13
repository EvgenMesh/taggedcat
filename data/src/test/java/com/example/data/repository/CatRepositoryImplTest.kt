package com.example.data.repository

import com.example.data.api.CatApi
import com.example.data.db.CatsDao
import com.example.data.utils.TestApiClient
import com.example.domain.entities.Cat
import com.example.domain.entities.Result
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue

class CatRepositoryImplTest {
    private val catsDao = mockk<CatsDao>()

    @Before
    fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true)

    @Test
    fun getDataSuccessfully() = runBlocking {
        every { catsDao.getCatsOrderedByDate() } returns emptyFlow()
        every { runBlocking { catsDao.insertAll(any()) } } just runs
        val catRepositoryImpl = CatRepositoryImpl(catsDao, CatApi(TestApiClient.httpClient))
        val resultFlow = catRepositoryImpl.getData()
        resultFlow.collect { result ->
            assertEquals(
                20,
                (result as Result.Success<List<Cat>>).value.size
            )
        }
    }

    @Test
    fun getDataWithError() = runBlocking {
        every { catsDao.getCatsOrderedByDate() } returns emptyFlow()
        val catRepositoryImpl =
            CatRepositoryImpl(catsDao, CatApi(TestApiClient.httpClientWithError))
        val resultFlow = catRepositoryImpl.getData()
        resultFlow.collect { result ->
            assertTrue {
                result is Result.Failure
            }
        }
    }
}