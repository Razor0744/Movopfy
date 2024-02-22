package com.example.movopfy.network.anilibria.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class AnilibriaPlayer(
    @SerialName("episodes") val episodes: JsonElement?,
    @SerialName("list") val list: JsonElement?
)