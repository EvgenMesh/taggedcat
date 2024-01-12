package com.example.data.mapper

import com.example.data.model.CatApiModel
import com.example.domain.entities.Cat

fun List<CatApiModel>.mapToCatList(): List<Cat> {
    return this.map { catFromApi -> Cat(catFromApi.title) }
}