package com.example.movopfy.features.search.data.repository

import com.example.movopfy.common.constants.API_CATEGORY_KINOPOISK
import com.example.movopfy.features.search.domain.models.SearchTitle
import com.example.movopfy.features.search.domain.repository.KinopoiskRepository
import com.example.movopfy.network.kinopoisk.service.KinopoiskService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

const val PAGE = 1

class KinopoiskRepositoryImpl(private val kinopoiskService: KinopoiskService) :
    KinopoiskRepository {

    override suspend fun search(searchText: String): List<SearchTitle> =
        withContext(Dispatchers.IO) {
            val response = kinopoiskService.searchTitles(page = PAGE, query = searchText)

            val responseBody = if (response.isSuccessful) response.body() else null

            responseBody?.docs?.map {
                SearchTitle(
                    id = it.id,
                    name = it.name,
                    imageUrl = it.poster.previewUrl ?: "",
                    category = API_CATEGORY_KINOPOISK
                )
            } ?: emptyList()
        }
}