package com.example.movopfy.features.movies.domain.repository

import com.example.movopfy.features.movies.domain.models.KinopoiskItem

interface KinopoiskRepository {

    suspend fun getList(page: Int, category: String): List<KinopoiskItem>
}