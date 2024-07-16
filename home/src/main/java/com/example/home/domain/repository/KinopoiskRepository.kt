package com.example.home.domain.repository

import com.example.home.domain.models.KinopoiskItem

interface KinopoiskRepository {

    suspend fun getList(page: Int, category: String): List<KinopoiskItem>
}