package com.example.movopfy.features.player.domain.models

import com.example.movopfy.database.models.player.PlayerMarks

data class PlayerState(
    val playerMarks: PlayerMarks?,
    val url: String,
    val episodesCount: Int,
    val episode: Int
)