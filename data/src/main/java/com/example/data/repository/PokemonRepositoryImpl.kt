package com.example.data.repository

import com.example.data.api.PokemonApi
import com.example.domain.entities.Pokemon
import com.example.domain.entities.Result
import com.example.domain.repository.PokemonRepository

class PokemonRepositoryImpl(private val api: PokemonApi) : PokemonRepository {
    override suspend fun getData(): Result<List<Pokemon>> {
        return try {
            val pokemonList = api.getPokemons()
            Result.Success(pokemonList)
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }
}