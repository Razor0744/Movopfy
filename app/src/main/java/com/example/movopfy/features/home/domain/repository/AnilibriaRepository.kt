package com.example.movopfy.features.home.domain.repository

import com.example.movopfy.features.home.data.models.AnilibriaSchedule

interface AnilibriaRepository {

    suspend fun getSchedule(): List<AnilibriaSchedule>
}