package com.example.movopfy.features.home.data.repository

import com.example.movopfy.features.home.domain.repository.KinopoiskRepository
import com.example.movopfy.network.kinopoisk.models.KinopoiskList
import com.example.movopfy.network.kinopoisk.models.KinopoiskTitle
import com.example.movopfy.network.kinopoisk.service.KinopoiskService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class KinopoiskRepositoryImpl(private val kinopoiskService: KinopoiskService) :
    KinopoiskRepository {

    override suspend fun getList(page: Int, limit: Int): KinopoiskList? =
        withContext(Dispatchers.IO) {
            val response = kinopoiskService.getList(page = page, limit = limit)
            if (response.isSuccessful) response.body() else null
        }

    override suspend fun getTitle(id: Int): KinopoiskTitle? = withContext(Dispatchers.IO) {
        val response = kinopoiskService.getTitle(id = id)
        if (response.isSuccessful) response.body() else null
    }
}