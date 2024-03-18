package com.example.presentation.models

import com.example.domain.entities.Pokemon

sealed class PokemonUIState {
    data class Success(val data : List<Pokemon>) : PokemonUIState()
    data class Error(val error : String) : PokemonUIState()
    data object Loading : PokemonUIState()
}