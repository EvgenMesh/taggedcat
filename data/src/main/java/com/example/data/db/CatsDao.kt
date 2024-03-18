package com.example.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.data.db.model.CatTable
import kotlinx.coroutines.flow.Flow

@Dao
interface CatsDao {
    @Query("SELECT * FROM CATS_TABLE")
    fun getAllCats(): Flow<List<CatTable>>

    @Query("SELECT * FROM CATS_TABLE ORDER BY datetime(published) DESC")
    fun getCatsOrderedByDate(): Flow<List<CatTable>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cats: List<CatTable>)

    @Upsert
    suspend fun upsertAll(cats: List<CatTable>)
}