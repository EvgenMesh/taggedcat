package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.db.model.CatTable

const val DB_NAME = "cats_db"
const val CATS_TABLE = "CATS_TABLE"
@Database(
    entities = [CatTable::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(MediaTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun catsDao(): CatsDao
}