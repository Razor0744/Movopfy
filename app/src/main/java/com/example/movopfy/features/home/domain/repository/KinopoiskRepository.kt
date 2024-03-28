package com.example.movopfy.features.home.domain.repository

import com.example.movopfy.network.kinopoisk.models.KinopoiskDocs

interface KinopoiskRepository {

    suspend fun getList(page: Int, category: String): List<KinopoiskDocs>
}