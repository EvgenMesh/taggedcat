package com.example.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.db.CATS_TABLE

@Entity(tableName = CATS_TABLE)
data class CatTable(
    @PrimaryKey val published: String,
    @ColumnInfo val description: String,
    @ColumnInfo val media: MediaDbModel,
    @ColumnInfo val link: String
)