package com.example.movopfy.features.home.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnilibriaHls(
    @SerialName("fhd") val fhd: String?,
    @SerialName("hd") val hd: String?,
    @SerialName("sd") val sd: String?

)