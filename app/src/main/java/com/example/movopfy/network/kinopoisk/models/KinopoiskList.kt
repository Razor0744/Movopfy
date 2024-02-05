package com.example.movopfy.network.kinopoisk.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KinopoiskList(
    @SerialName("docs") val docs: ArrayList<KinopoiskDocs>?,
    @SerialName("total") val total: Int?,
    @SerialName("limit") val limit: Int?,
    @SerialName("page") val page: Int?,
    @SerialName("pages") val pages: Int?
)
