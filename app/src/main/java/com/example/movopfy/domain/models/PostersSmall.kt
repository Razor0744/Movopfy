package com.example.movopfy.domain.models

import com.google.gson.annotations.SerializedName

data class PostersSmall(
    @SerializedName("url") var url: String? = null,
    @SerializedName("raw_base64_file") var rawBase64File: String? = null
)
