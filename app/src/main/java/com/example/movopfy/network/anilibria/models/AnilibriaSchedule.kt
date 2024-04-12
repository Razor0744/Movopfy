package com.example.movopfy.network.anilibria.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnilibriaSchedule(
    @SerialName("day") val day: Int?,
    @SerialName("list") val list: List<AnilibriaTitle>?
)