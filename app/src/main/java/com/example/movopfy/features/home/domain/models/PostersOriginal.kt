package com.example.movopfy.features.home.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostersOriginal(
    @SerialName("url") val url: String,
    @SerialName("raw_base64_file") val rawBase64File: String?
)
