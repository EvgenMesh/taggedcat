package com.example.data.repository

import com.example.data.api.CatApi
import com.example.data.db.CatsDao
import com.example.data.mapper.mapToCatList
import com.example.data.mapper.mapToCatTable
import com.example.domain.entities.Cat
import com.example.domain.entities.Result
import com.example.domain.repository.CatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge

class CatRepositoryImpl(private val catsDAO: CatsDao, private val api: CatApi) : CatRepository {

    override fun getData(): Flow<Result<List<Cat>>> {
        return merge(getDataFromDB(), getDataFromApi())
    }

    override fun getDataFromApi(): Flow<Result<List<Cat>>> = flow {
        try {
            catsDAO.insertAll(api.getCats().mapToCatTable())
        } catch (e: Exception) {
            emit(Result.Failure(e))
        }
    }

    override fun getDataFromDB(): Flow<Result<List<Cat>>> {
        return catsDAO.getCatsOrderedByDate().map { Result.Success(it.mapToCatList()) }
            .catch { error ->
                Result.Failure(error)
            }
    }
}