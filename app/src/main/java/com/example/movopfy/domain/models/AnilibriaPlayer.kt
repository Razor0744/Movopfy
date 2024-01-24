package com.example.movopfy.domain.models

import com.google.gson.annotations.SerializedName

data class AnilibriaPlayer(
    @SerializedName("episodes") var episodes: AnilibriaEpisodes? = null,
    @SerializedName("list") var list: List<AnilibriaEpisodesList>? = null
)
