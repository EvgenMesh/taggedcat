package com.example.presentation.mapper

import com.example.domain.entities.Cat
import com.example.presentation.model.CatUIModel

fun List<Cat>.mapToUIModel(): List<CatUIModel> =
    this.map { cat ->
        CatUIModel(
            cat.description,
            cat.media.m,
            cat.published,
            cat.link
        )
    }