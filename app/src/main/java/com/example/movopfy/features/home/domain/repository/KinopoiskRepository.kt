package com.example.movopfy.features.home.domain.repository

import com.example.movopfy.network.kinopoisk.models.KinopoiskDocs
import com.example.movopfy.network.kinopoisk.models.KinopoiskTitle

interface KinopoiskRepository {

    suspend fun getList(page: Int, category: String): List<KinopoiskDocs>

    suspend fun getTitle(id: Int): KinopoiskTitle?
}