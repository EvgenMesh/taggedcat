package com.example.pokemons

import com.example.domain.entities.Pokemon
import com.example.domain.entities.Result
import com.example.domain.usecase.GetPokemonDataUseCase
import com.example.presentation.models.PokemonUIState
import com.example.presentation.ui.PokemonViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class PokemonViewModelTest {
    private val getPokemonsUseCase = mockk<GetPokemonDataUseCase>()
    private val dispatcher = UnconfinedTestDispatcher()

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
            Pokemon(
                "test"
            )
        )
        every { runBlocking { getPokemonsUseCase.invoke() } } returns Result.Success(testData)
        val viewModel = PokemonViewModel(getPokemonsUseCase)
        assertTrue { viewModel.uiState.value is PokemonUIState.Success }
        TestCase.assertEquals(
            testData.size,
            (viewModel.uiState.value as PokemonUIState.Success).data.size
        )
        TestCase.assertEquals(
            "test",
            (viewModel.uiState.value as PokemonUIState.Success).data[0].name
        )
    }

    @Test
    fun `fetch data unsuccessfully`() {
        every { runBlocking { getPokemonsUseCase.invoke() } } returns Result.Failure(
            NullPointerException()
        )
        val viewModel = PokemonViewModel(getPokemonsUseCase)
        assertTrue { viewModel.uiState.value is PokemonUIState.Error }
    }
}