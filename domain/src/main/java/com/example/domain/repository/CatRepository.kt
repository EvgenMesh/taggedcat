package com.example.domain.repository

import com.example.domain.entities.Cat
import com.example.domain.entities.Result
import kotlinx.coroutines.flow.Flow

interface CatRepository {
    fun getData() : Flow<Result<List<Cat>>>
    fun getDataFromApi() : Flow<Result<List<Cat>>>
    fun getDataFromDB() : Flow<Result<List<Cat>>>
}