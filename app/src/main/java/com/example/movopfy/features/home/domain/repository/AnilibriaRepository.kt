package com.example.movopfy.features.home.domain.repository

import com.example.common.models.AnimeSeries

interface AnilibriaRepository {

    suspend fun getAnimeSeriesList(currentDay: Int, dateTime: Int): List<com.example.common.models.AnimeSeries>
}