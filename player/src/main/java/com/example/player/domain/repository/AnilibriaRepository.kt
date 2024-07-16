package com.example.player.domain.repository

import com.example.player.domain.models.PlayerData

interface AnilibriaRepository {

    suspend fun getPlayerData(id: Int, episode: Int): PlayerData
}