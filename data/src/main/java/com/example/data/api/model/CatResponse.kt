package com.example.data.api.model

import kotlinx.serialization.Serializable

@Serializable
data class CatResponse(val items : List<CatApiModel>)