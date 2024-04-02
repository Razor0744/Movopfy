package com.example.movopfy.features.movies.data.repository

import com.example.movopfy.features.movies.domain.repository.KinopoiskRepository
import com.example.movopfy.network.kinopoisk.models.KinopoiskDocs
import com.example.movopfy.network.kinopoisk.service.KinopoiskService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class KinopoiskRepositoryImpl(private val kinopoiskService: KinopoiskService): KinopoiskRepository {

    override suspend fun getList(page: Int, category: String): List<KinopoiskDocs> =
        withContext(Dispatchers.IO) {
            val response = kinopoiskService.getList(page = page, category = category)

            if (response.isSuccessful) response.body()?.docs ?: emptyList() else emptyList()
        }
}