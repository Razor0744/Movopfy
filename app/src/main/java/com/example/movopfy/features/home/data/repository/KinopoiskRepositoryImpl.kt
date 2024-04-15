package com.example.movopfy.features.home.data.repository

import com.example.movopfy.database.dao.home.KinopoiskDocsDao
import com.example.movopfy.database.models.home.Kinopoisk
import com.example.movopfy.features.home.domain.models.KinopoiskItem
import com.example.movopfy.features.home.domain.repository.KinopoiskRepository
import com.example.movopfy.network.kinopoisk.service.KinopoiskService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

class KinopoiskRepositoryImpl(
    private val kinopoiskService: KinopoiskService,
    private val kinopoiskDocsDao: KinopoiskDocsDao
) : KinopoiskRepository {

    private val kinopoiskItemsListMutex = Mutex()

    private var kinopoiskItemList: List<KinopoiskItem> = emptyList()

    override suspend fun getList(page: Int, category: String): List<KinopoiskItem> =
        withContext(Dispatchers.IO) {
            kinopoiskItemsListMutex.withLock {
                val localList =
                    if (kinopoiskItemList.isEmpty()) kinopoiskDocsDao.getKinopoiskDocsByCategory(
                        category = category
                    )
                    else emptyList()

                when {
                    localList.isNotEmpty() -> {
                        kinopoiskItemList = localList.map {
                            KinopoiskItem(
                                id = it.id,
                                previewUrl = it.previewUrl
                            )
                        }

                        kinopoiskItemList
                    }

                    else -> {
                        val response = kinopoiskService.getList(page = page, category = category)

                        val responseBody = if (response.isSuccessful) response.body() else null

                        responseBody?.let { item ->
                            kinopoiskItemList = item.docs
                                ?.filter { it.id != null && it.poster != null && it.poster.previewUrl != null }
                                ?.map {
                                    KinopoiskItem(
                                        id = it.id ?: 0,
                                        previewUrl = it.poster?.previewUrl ?: ""
                                    )
                                }
                                ?: emptyList()

                            item.docs
                                ?.filter { it.id != null && it.poster != null && it.poster.previewUrl != null }
                                ?.map {
                                    Kinopoisk(
                                        id = it.id ?: 0,
                                        category = category,
                                        previewUrl = it.poster?.previewUrl ?: ""
                                    )
                                }
                                ?.toTypedArray()
                                ?.let { kinopoiskDocsDao.addKinopoiskDocs(kinopoisk = it) }
                        }

                        kinopoiskItemList
                    }
                }
            }
        }
}