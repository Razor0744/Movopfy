package com.example.movopfy.features.home.domain.repository

import com.example.movopfy.network.kinopoisk.models.KinopoiskList
import com.example.movopfy.network.kinopoisk.models.KinopoiskTitle

interface KinopoiskRepository {

    suspend fun getList(page: Int, limit: Int): KinopoiskList?

    suspend fun getTitle(id: Int): KinopoiskTitle?
}