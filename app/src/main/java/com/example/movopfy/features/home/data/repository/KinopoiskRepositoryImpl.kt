package com.example.movopfy.features.home.data.repository

import com.example.movopfy.database.dao.home.KinopoiskDocsDao
import com.example.movopfy.database.models.home.Kinopoisk
import com.example.movopfy.features.home.domain.repository.KinopoiskRepository
import com.example.movopfy.network.kinopoisk.models.KinopoiskDocs
import com.example.movopfy.network.kinopoisk.models.KinopoiskMoviePoster
import com.example.movopfy.network.kinopoisk.service.KinopoiskService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

class KinopoiskRepositoryImpl(
    private val kinopoiskService: KinopoiskService,
    private val kinopoiskDocsDao: KinopoiskDocsDao
) : KinopoiskRepository {

    private val kinopoiskDocsListMutex = Mutex()

    private var kinopoiskDocsList: List<KinopoiskDocs> = emptyList()

    override suspend fun getList(page: Int, category: String): List<KinopoiskDocs> =
        withContext(Dispatchers.IO) {
            kinopoiskDocsListMutex.withLock {
                val localList =
                    if (kinopoiskDocsList.isEmpty()) kinopoiskDocsDao.getKinopoiskDocsByCategory(
                        category = category
                    )
                    else emptyList()

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

                            kinopoiskDocsDao.addKinopoiskDocs(kinopoiskDocs = item.docs.map {
                                Kinopoisk(
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
}