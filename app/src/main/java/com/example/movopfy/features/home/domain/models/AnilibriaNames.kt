package com.example.movopfy.features.home.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnilibriaNames(
    @SerialName("ru") val ru: String?
)
