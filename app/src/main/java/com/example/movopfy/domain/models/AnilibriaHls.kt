package com.example.movopfy.domain.models

import com.google.gson.annotations.SerializedName

data class AnilibriaHls(
    @SerializedName("fhd") var fhd: String? = null,
    @SerializedName("hd") var hd: String? = null,
    @SerializedName("sd") var sd: String? = null

)
