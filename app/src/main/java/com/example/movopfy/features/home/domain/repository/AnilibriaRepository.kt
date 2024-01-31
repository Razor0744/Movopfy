package com.example.movopfy.features.home.domain.repository

import com.example.movopfy.network.anilibria.models.AnilibriaSchedule

interface AnilibriaRepository {

    suspend fun getSchedule(): List<AnilibriaSchedule>
}