package com.example.movopfy.features.player.domain.models

data class PlayerState(
    val currentTime: Long,
    val url: String,
    val episodesCount: Int,
    val episode: Int
)