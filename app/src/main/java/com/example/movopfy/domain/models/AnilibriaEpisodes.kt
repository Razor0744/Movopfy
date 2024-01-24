package com.example.movopfy.domain.models

import com.google.gson.annotations.SerializedName

data class AnilibriaEpisodes(
    @SerializedName("first") var first: Int? = null,
    @SerializedName("last") var last: Int? = null,
    @SerializedName("string") var string: String? = null
)
