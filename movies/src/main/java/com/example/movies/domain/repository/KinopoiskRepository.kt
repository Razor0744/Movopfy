package com.example.movies.domain.repository

import com.example.movies.domain.models.KinopoiskItem

interface KinopoiskRepository {

    suspend fun getList(page: Int, category: String): List<KinopoiskItem>
}