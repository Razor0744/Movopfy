package com.example.movopfy.network.kinopoisk.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KinopoiskSearch(
    @SerialName("docs") val docs: List<SearchDocs>
)