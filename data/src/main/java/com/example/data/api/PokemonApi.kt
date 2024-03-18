package com.example.data.api

import com.example.data.api.model.CatApiModel
import com.example.data.api.model.CatResponse
import com.example.data.api.model.mapToPokemonData
import com.example.domain.entities.Pokemon
import com.example.presentation.repository.api.model.PokemonApiResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

private const val POKEMON_API_URL =
    "https://pokeapi.co/api/v2/pokemon?limit=100000&offset=0"

class PokemonApi(private val httpClient: HttpClient) {
    suspend fun getPokemons(): List<Pokemon> {
        return httpClient.get(POKEMON_API_URL).body<PokemonApiResponse>().results.mapToPokemonData()
    }
}