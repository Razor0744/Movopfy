package com.example.network.kinopoisk.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KinopoiskHD(
    @SerialName("kpHD") val kinopoiskHD: String?
)