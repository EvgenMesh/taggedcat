package com.example.data.api.model

import com.example.domain.entities.Pokemon
import com.example.presentation.repository.api.model.PokemonApiModel

fun List<PokemonApiModel>.mapToPokemonData(): List<Pokemon> {
    return this.map { api -> Pokemon(api.name) }
}