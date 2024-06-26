package com.example.network.anilibria.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostersSmall(
    @SerialName("url") val url: String?,
    @SerialName("raw_base64_file") val rawBase64File: String?
)