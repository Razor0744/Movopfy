package com.example.movopfy.features.player.domain.repository

import com.example.movopfy.features.player.domain.models.PlayerMark

interface PlayerMarksRepository {

    suspend fun getTimeById(id: Int, episode: Int): Long

    suspend fun setTime(playerMark: PlayerMark)
}