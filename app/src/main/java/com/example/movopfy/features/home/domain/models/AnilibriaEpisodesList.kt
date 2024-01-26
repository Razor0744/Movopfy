package com.example.movopfy.features.home.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnilibriaEpisodesList(
    @SerialName("episode") val episode: Int?,
    @SerialName("name") val name: String?,
    @SerialName("uuid") val uuid: String?,
    @SerialName("created_timestamp") val createdTimestamp: Int?,
    @SerialName("preview") val preview: String?,
    @SerialName("hls") val hls: AnilibriaHls?,
)
