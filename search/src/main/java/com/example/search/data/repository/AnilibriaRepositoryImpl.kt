package com.example.search.data.repository

import com.example.common.extensions.getSmallImageUrl
import com.example.network.anilibria.service.AnilibriaService
import com.example.search.domain.models.SearchTitle
import com.example.search.domain.repository.AnilibriaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AnilibriaRepositoryImpl(private val anilibriaService: AnilibriaService) :
    AnilibriaRepository {

    override suspend fun search(searchText: String): List<SearchTitle> =
        withContext(Dispatchers.IO) {
            val response = anilibriaService.searchTitles(searchText = searchText)

            val responseBody = if (response.isSuccessful) response.body() else null

            responseBody?.list?.map {
                SearchTitle(
                    id = it.id ?: 0,
                    name = it.anilibriaNames?.ru ?: "",
                    imageUrl = it.getSmallImageUrl() ?: "",
                    category = com.example.common.constants.API_CATEGORY_ANILIBRIA
                )
            } ?: emptyList()
        }
}