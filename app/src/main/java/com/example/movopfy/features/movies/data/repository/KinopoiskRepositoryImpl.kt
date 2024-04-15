package com.example.movopfy.features.movies.data.repository

import com.example.movopfy.features.movies.domain.models.KinopoiskItems
import com.example.movopfy.features.movies.domain.repository.KinopoiskRepository
import com.example.movopfy.network.kinopoisk.service.KinopoiskService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class KinopoiskRepositoryImpl(private val kinopoiskService: KinopoiskService) :
    KinopoiskRepository {

    override suspend fun getList(page: Int, category: String): List<KinopoiskItems> =
        withContext(Dispatchers.IO) {
            val response = kinopoiskService.getList(page = page, category = category)

            if (response.isSuccessful) response.body()?.docs
                ?.filter { it.id != null && it.poster != null && it.poster.previewUrl != null }
                ?.map { KinopoiskItems(id = it.id ?: 0, previewUrl = it.poster?.previewUrl ?: "") }
                ?: emptyList()
            else emptyList()
        }
}