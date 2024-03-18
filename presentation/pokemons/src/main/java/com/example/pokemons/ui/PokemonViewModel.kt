package com.example.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.Result
import com.example.domain.usecase.GetPokemonDataUseCase
import com.example.presentation.models.PokemonUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(private val useCase: GetPokemonDataUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow<PokemonUIState>(PokemonUIState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            when (val result = useCase.invoke()) {
                is Result.Failure -> {
                    _uiState.value = PokemonUIState.Error(result.error.message?: "Unspecified error")
                }
                is Result.Success -> {
                    _uiState.value = PokemonUIState.Success(result.value)
                }
            }
        }
    }
}