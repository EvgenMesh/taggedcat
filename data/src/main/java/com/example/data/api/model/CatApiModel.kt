package com.example.data.api.model

import kotlinx.serialization.Serializable

@Serializable
data class CatApiModel(val published : String, val description : String, val media : MediaApiModel, val link : String)
