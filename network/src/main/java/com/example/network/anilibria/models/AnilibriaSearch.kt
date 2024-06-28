package com.example.network.anilibria.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnilibriaSearch(
    @SerialName("list") val list: List<com.example.network.anilibria.models.AnilibriaTitle>
)