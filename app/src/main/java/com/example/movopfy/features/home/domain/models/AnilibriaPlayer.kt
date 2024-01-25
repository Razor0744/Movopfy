package com.example.movopfy.features.home.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnilibriaPlayer(
    @SerialName("episodes") val episodes: AnilibriaEpisodes?,
    @SerialName("list") val list: AnilibriaEpisodesList?
)
