package com.example.presentation.repository.api.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonApiResponse(val results : List<PokemonApiModel>)
