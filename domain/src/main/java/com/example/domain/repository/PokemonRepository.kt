package com.example.domain.repository

import com.example.domain.entities.Pokemon
import com.example.domain.entities.Result

interface PokemonRepository {
    suspend fun getData() : Result<List<Pokemon>>
}