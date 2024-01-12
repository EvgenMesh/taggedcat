package com.example.domain.repository

import com.example.domain.entities.Cat
import com.example.domain.entities.Result

interface CatRepository {
    suspend fun getData() : Result<List<Cat>>
}