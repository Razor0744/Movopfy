package com.example.movopfy.features.home.domain.repository

import com.example.movopfy.common.models.AnimeSeries

interface AnilibriaRepository {

    suspend fun getAnimeSeriesList(currentDay: Int, dateTime: Int): List<AnimeSeries>
}