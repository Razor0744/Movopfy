package com.example.movopfy.features.home.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnilibriaTitle(
    @SerialName("id") val id: Int?,
    @SerialName("names") val anilibriaNames: AnilibriaNames?,
    @SerialName("posters") val anilibriaPosters: AnilibriaPosters?,
    @SerialName("player") val player: AnilibriaPlayer?
)