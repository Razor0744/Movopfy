package com.example.movopfy.domain.models

import com.google.gson.annotations.SerializedName

data class AnilibriaPosters(
    @SerializedName("small") var small: PostersSmall? = null,
    @SerializedName("medium") var medium: PostersMedium? = null,
    @SerializedName("original") var original: PostersOriginal? = null
)
