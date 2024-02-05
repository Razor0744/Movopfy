package com.example.movopfy.features.home.domain.repository

import com.example.movopfy.network.kinopoisk.models.KinopoiskList

interface KinopoiskRepository {

    suspend fun getList(page: Int, limit: Int): KinopoiskList?
}