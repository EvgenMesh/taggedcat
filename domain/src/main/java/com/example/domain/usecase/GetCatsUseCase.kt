package com.example.domain.usecase

import com.example.domain.entities.Cat
import com.example.domain.entities.Result
import com.example.domain.repository.CatRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class GetCatsUseCase(private val repository: CatRepository, private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {
    operator fun invoke(): Flow<Result<List<Cat>>> = repository.getData().flowOn(dispatcher)
}