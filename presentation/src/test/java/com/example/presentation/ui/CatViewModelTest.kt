package com.example.presentation.ui

import com.example.domain.entities.Cat
import com.example.domain.entities.Media
import com.example.domain.entities.Result
import com.example.domain.usecase.GetCatsUseCase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class CatViewModelTest {
    private val getCatsUseCase = mockk<GetCatsUseCase>()
    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetch data successfully`() {
        val testData = listOf(
            Cat(
                "test",
                Media("test"), "test", "test"
            )
        )
        runBlocking {
            every { getCatsUseCase.invoke() } returns flow {
                emit(
                    Result.Success(
                        testData
                    )
                )
            }
            val viewModel = CatViewModel(getCatsUseCase)
            assertTrue { viewModel.uiState.value is CatViewModel.UIState.Success }
            assertEquals(
                testData.size,
                (viewModel.uiState.value as CatViewModel.UIState.Success).data.size
            )
            assertEquals(
                testData[0].link,
                (viewModel.uiState.value as CatViewModel.UIState.Success).data[0].link
            )
            assertEquals(
                testData[0].description,
                (viewModel.uiState.value as CatViewModel.UIState.Success).data[0].description
            )
            assertEquals(
                testData[0].published,
                (viewModel.uiState.value as CatViewModel.UIState.Success).data[0].published
            )
            assertEquals(
                testData[0].media.m,
                (viewModel.uiState.value as CatViewModel.UIState.Success).data[0].mediaUrl
            )
        }
    }

    @Test
    fun `fetch data unsuccessfully`() {
        runBlocking {
            every { getCatsUseCase.invoke() } returns flow {
                emit(
                    Result.Failure(
                        NullPointerException()
                    )
                )
            }
            val viewModel = CatViewModel(getCatsUseCase)
            assertTrue { viewModel.uiState.value is CatViewModel.UIState.Error }
        }
    }
}