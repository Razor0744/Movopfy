package com.example.movopfy.features.search.data.repository

import com.example.movopfy.common.constants.API_CATEGORY_ANILIBRIA
import com.example.movopfy.common.extensions.getSmallImageUrl
import com.example.movopfy.features.search.domain.models.SearchTitle
import com.example.movopfy.features.search.domain.repository.AnilibriaRepository
import com.example.movopfy.network.anilibria.service.AnilibriaService
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
                    id = it.id,
                    name = it.anilibriaNames?.ru ?: "",
                    imageUrl = it.getSmallImageUrl() ?: "",
                    category = API_CATEGORY_ANILIBRIA
                )
            } ?: emptyList()
        }
}