package com.example.domain.usecase

import com.example.domain.entities.Pokemon
import com.example.domain.entities.Result
import com.example.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonDataUseCase @Inject constructor(private val repository: PokemonRepository) {

    suspend operator fun invoke(): Result<List<Pokemon>> = repository.getData()
}