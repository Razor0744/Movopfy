package com.example.movopfy.features.player.domain.repository

import com.example.movopfy.features.player.domain.models.PlayerData

interface AnilibriaRepository {

    suspend fun getPlayerData(id: Int, episode: Int): PlayerData
}