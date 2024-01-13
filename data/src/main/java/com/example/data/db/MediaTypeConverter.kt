package com.example.data.db

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.data.db.model.MediaDbModel

@ProvidedTypeConverter
class MediaTypeConverter {
    @TypeConverter
    fun stringToMedia(string: String): MediaDbModel {
        return MediaDbModel(string)
    }

    @TypeConverter
    fun mediaToString(media: MediaDbModel): String {
        return media.m
    }
}