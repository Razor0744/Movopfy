package com.example.movopfy.features.home.domain.repository

import com.example.movopfy.features.home.domain.models.KinopoiskItem

interface KinopoiskRepository {

    suspend fun getList(page: Int, category: String): List<KinopoiskItem>
}