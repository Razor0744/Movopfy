package com.example.movopfy.features.anime.domain.repository

import com.example.movopfy.network.anilibria.models.AnilibriaSchedule

interface AnilibriaRepository {

    suspend fun getSchedules(): List<AnilibriaSchedule>
}