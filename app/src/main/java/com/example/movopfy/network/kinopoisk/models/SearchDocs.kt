package com.example.movopfy.network.kinopoisk.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchDocs(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("poster") val poster: KinopoiskMoviePoster
)
