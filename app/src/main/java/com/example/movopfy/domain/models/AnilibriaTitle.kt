package com.example.movopfy.domain.models

import com.google.gson.annotations.SerializedName

data class AnilibriaTitle(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("names") var anilibriaNames: AnilibriaNames? = null,
    @SerializedName("posters") var anilibriaPosters: AnilibriaPosters? = null,
    @SerializedName("player") var player: AnilibriaPlayer? = null,
)