package com.example.domain.usecase

import com.example.domain.entities.Cat
import com.example.domain.entities.Result
import com.example.domain.repository.CatRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetCatsUseCase @Inject constructor(private val repository: CatRepository, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) {
    operator fun invoke(): Flow<Result<List<Cat>>> = repository.getData().flowOn(ioDispatcher)
}