package com.example.movopfy.features.player.domain.repository

import com.example.movopfy.database.models.player.PlayerMarks

interface PlayerMarksRepository {

    suspend fun getTimeById(id: Int, episode: Int): PlayerMarks?

    suspend fun setTime(playerMarks: PlayerMarks)
}