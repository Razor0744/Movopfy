package com.example.network.anilibria.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnilibriaEpisodes(
    @SerialName("first") val first: Int?,
    @SerialName("last") val last: Int?,
    @SerialName("string") val string: String?,
)