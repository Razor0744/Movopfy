package com.example.movopfy.features.details.domain.repository

import com.example.movopfy.network.kinopoisk.models.KinopoiskTitle

interface KinopoiskRepository {

    suspend fun getTitle(id: Int): KinopoiskTitle?
}