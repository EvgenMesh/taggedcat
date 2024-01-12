package com.example.domain.usecase

import com.example.domain.entities.Cat
import com.example.domain.entities.Result
import com.example.domain.repository.CatRepository

class GetCatsUseCase(private val repository: CatRepository) {
    suspend operator fun invoke(): Result<List<Cat>> = repository.getData()
}