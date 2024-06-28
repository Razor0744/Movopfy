package com.example.network.kinopoisk.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KinopoiskSearch(
    @SerialName("docs") val docs: List<com.example.network.kinopoisk.models.SearchDocs>
)