package com.example.movopfy.features.home.data.repository

import com.example.movopfy.database.dao.home.KinopoiskDocsDao
import com.example.movopfy.database.models.home.RoomKinopoiskDocs
import com.example.movopfy.features.home.domain.repository.KinopoiskRepository
import com.example.movopfy.network.kinopoisk.models.KinopoiskDocs
import com.example.movopfy.network.kinopoisk.models.KinopoiskMoviePoster
import com.example.movopfy.network.kinopoisk.service.KinopoiskService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class KinopoiskRepositoryImpl(
    private val kinopoiskService: KinopoiskService,
    private val kinopoiskDocsDao: KinopoiskDocsDao
) : KinopoiskRepository {

    private var kinopoiskDocsList: List<KinopoiskDocs> = emptyList()

    override suspend fun getList(page: Int, category: String): List<KinopoiskDocs> =
        withContext(Dispatchers.IO) {
            val localList = kinopoiskDocsDao.getKinopoiskDocsByCategory(category = category)

            when {
                localList.isNotEmpty() -> {
                    kinopoiskDocsList = localList.map {
                        KinopoiskDocs(
                            id = it.id,
                            poster = KinopoiskMoviePoster(
                                url = null,
                                previewUrl = it.previewUrl
                            )
                        )
                    }

                    kinopoiskDocsList
                }

                else -> {
                    val response = kinopoiskService.getList(page = page, category = category)

                    val responseBody = if (response.isSuccessful) response.body() else null

                    responseBody?.let { item ->
                        kinopoiskDocsList = responseBody.docs

                        kinopoiskDocsDao.addKinopoiskDocs(roomKinopoiskDocs = item.docs.map {
                            RoomKinopoiskDocs(
                                id = it.id,
                                category = category,
                                previewUrl = it.poster?.previewUrl ?: ""
                            )
                        }.toTypedArray())
                    }

                    kinopoiskDocsList
                }
            }
        }
}