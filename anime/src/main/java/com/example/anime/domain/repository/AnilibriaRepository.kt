package com.example.anime.domain.repository

import com.example.common.models.AnimeSeries

interface AnilibriaRepository {

    suspend fun getSchedules(dateTime: Int): List<List<AnimeSeries>>
}