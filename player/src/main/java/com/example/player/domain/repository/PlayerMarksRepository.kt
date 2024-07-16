package com.example.player.domain.repository

import com.example.database.models.player.PlayerMarks

interface PlayerMarksRepository {

    suspend fun getTimeById(id: Int, episode: Int): PlayerMarks?

    suspend fun setTime(playerMarks: PlayerMarks)
}