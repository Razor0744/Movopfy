package com.example.movopfy.features.anime.domain.repository

import com.example.movopfy.common.models.AnimeSeries

interface AnilibriaRepository {

    suspend fun getSchedules(dateTime: Int): List<List<AnimeSeries>>
}