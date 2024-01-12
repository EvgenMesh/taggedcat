package com.example.data.repository

import com.example.data.api.CatApi
import com.example.domain.entities.Cat
import com.example.domain.entities.Result
import com.example.domain.repository.CatRepository

class CatRepositoryImpl(private val api: CatApi) : CatRepository {

    override suspend fun getData(): Result<List<Cat>> {
        return try {
            val data = api.getCats()
            Result.Success(data)
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }
}