package com.example.movopfy.network.kinopoisk.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KinopoiskDocs(
    @SerialName("id") val id: Int?,
    @SerialName("poster") val poster: KinopoiskMoviePoster?
)