package com.example.data.mapper

import com.example.data.api.model.CatApiModel
import com.example.data.db.model.CatTable
import com.example.data.db.model.MediaDbModel
import com.example.domain.entities.Cat
import com.example.domain.entities.Media

fun List<CatTable>.mapToCatList(): List<Cat> {
    return this.map { catFromDB -> Cat(catFromDB.description, Media(catFromDB.media.m), catFromDB.published, catFromDB.link) }
}

fun List<CatApiModel>.mapToCatTable(): List<CatTable> {
    return this.map { catFromApi ->
        CatTable(
            catFromApi.published,
            catFromApi.description,
            MediaDbModel(catFromApi.media.m),
            catFromApi.link
        )
    }
}