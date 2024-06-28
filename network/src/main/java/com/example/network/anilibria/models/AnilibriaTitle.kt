package com.example.network.anilibria.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnilibriaTitle(
    @SerialName("id") val id: Int?,
    @SerialName("names") val anilibriaNames: com.example.network.anilibria.models.AnilibriaNames?,
    @SerialName("posters") val anilibriaPosters: com.example.network.anilibria.models.AnilibriaPosters?,
    @SerialName("player") val player: com.example.network.anilibria.models.AnilibriaPlayer?,
    @SerialName("description") val description: String?
)