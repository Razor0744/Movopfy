package com.example.movopfy.network.kinopoisk.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KinopoiskMoviePoster(
    @SerialName("url") val url: String?,
    @SerialName("previewUrl") val previewUrl: String?
)