package com.example.player.domain.models

import com.example.database.models.player.PlayerMarks

data class PlayerState(
    val playerMarks: PlayerMarks?,
    val url: String,
    val episodesCount: Int,
    val episode: Int
)