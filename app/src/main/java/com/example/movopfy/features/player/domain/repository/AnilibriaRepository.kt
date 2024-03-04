package com.example.movopfy.features.player.domain.repository

import com.example.movopfy.network.anilibria.models.AnilibriaTitle

interface AnilibriaRepository {

    suspend fun getTitle(id: Int): AnilibriaTitle?
}