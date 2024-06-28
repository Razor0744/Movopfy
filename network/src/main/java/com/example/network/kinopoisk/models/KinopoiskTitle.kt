package com.example.network.kinopoisk.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KinopoiskTitle(
    @SerialName("poster") val poster: com.example.network.kinopoisk.models.KinopoiskMoviePoster?,
    @SerialName("name") val name: String?,
    @SerialName("description") val description: String?,
    @SerialName("externalId") val kinopoiskHD: com.example.network.kinopoisk.models.KinopoiskHD?
)