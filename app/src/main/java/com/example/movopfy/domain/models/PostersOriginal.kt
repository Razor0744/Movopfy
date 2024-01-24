package com.example.movopfy.domain.models

import com.google.gson.annotations.SerializedName

data class PostersOriginal(
    @SerializedName("url") val url: String,
    @SerializedName("raw_base64_file") var rawBase64File: String? = null
)
