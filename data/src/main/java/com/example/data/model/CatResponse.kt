package com.example.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CatResponse(val items : List<CatApiModel>)