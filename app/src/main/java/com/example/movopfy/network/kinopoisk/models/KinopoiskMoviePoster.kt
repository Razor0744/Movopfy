package com.example.movopfy.network.kinopoisk.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KinopoiskMoviePoster(
    @SerialName("url") var url: String? = null,
    @SerialName("previewUrl") var previewUrl: String? = null
)