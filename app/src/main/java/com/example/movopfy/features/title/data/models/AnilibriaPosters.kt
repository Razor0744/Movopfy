package com.example.movopfy.features.title.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnilibriaPosters(
    @SerialName("small") val small: PostersSmall?,
    @SerialName("medium") val medium: PostersMedium?,
    @SerialName("original") val original: PostersOriginal?,
)