package com.example.movopfy.domain.models

import com.google.gson.annotations.SerializedName

data class AnilibriaEpisodesList(
    @SerializedName("episode") var episode: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("uuid") var uuid: String? = null,
    @SerializedName("created_timestamp") var createdTimestamp: Int? = null,
    @SerializedName("preview") var preview: String? = null,
    @SerializedName("hls") var hls: AnilibriaHls? = null
)
