package com.example.movopfy.network.kinopoisk.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KinopoiskTitle(
    @SerialName("kpHD") val kinopoiskHd: String?,
    @SerialName("name") val name: String?,
    @SerialName("description") val description: String?,
    @SerialName("externalId") val kinopoiskHD: KinopoiskHD?
)