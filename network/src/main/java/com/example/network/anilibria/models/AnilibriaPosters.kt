package com.example.network.anilibria.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnilibriaPosters(
    @SerialName("small") val small: com.example.network.anilibria.models.PostersSmall?,
    @SerialName("medium") val medium: com.example.network.anilibria.models.PostersMedium?,
    @SerialName("original") val original: com.example.network.anilibria.models.PostersOriginal?,
)