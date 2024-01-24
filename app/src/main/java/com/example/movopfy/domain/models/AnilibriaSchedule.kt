package com.example.movopfy.domain.models

import com.google.gson.annotations.SerializedName

data class AnilibriaSchedule(
    @SerializedName("day") var day: Int? = null,
    @SerializedName("list") var list: List<List<AnilibriaTitle>>
)
