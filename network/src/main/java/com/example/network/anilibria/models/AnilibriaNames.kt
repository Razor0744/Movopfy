package com.example.network.anilibria.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnilibriaNames(
    @SerialName("ru") val ru: String?
)