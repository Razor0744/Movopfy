package com.example.movopfy.features.details.data.repository

import com.example.movopfy.common.constants.API_CATEGORY_KINOPOISK
import com.example.movopfy.database.dao.details.DetailsDao
import com.example.movopfy.database.models.details.Details
import com.example.movopfy.features.details.domain.models.DetailsData
import com.example.movopfy.features.details.domain.repository.KinopoiskRepository
import com.example.movopfy.network.kinopoisk.service.KinopoiskService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

class KinopoiskRepositoryImpl(
    private val kinopoiskService: KinopoiskService,
    private val detailsDao: DetailsDao
) : KinopoiskRepository {

    private val detailsDataMutex = Mutex()

    private var detailsData: DetailsData? = null
    private var lastTitleId: Int? = null

    override suspend fun getTitle(id: Int, dateTime: Int): DetailsData? = withContext(Dispatchers.IO) {
        detailsDataMutex.withLock {
            var localState =
                if (detailsData == null || lastTitleId != id) detailsDao.getTitleById(
                    id = id,
                    category = API_CATEGORY_KINOPOISK
                )
                else null

            if (localState?.details?.lastUpdate != dateTime){
                localState = null
            }

            when {
                detailsData != null && lastTitleId == id -> {
                    detailsData
                }

                localState != null -> {
                    detailsData = DetailsData(
                        url = localState.details.pictureUrl,
                        name = localState.details.name,
                        description = localState.details.description,
                        episodesList = null
                    )

                    lastTitleId = id

                    detailsData
                }

                else -> {
                    val response = kinopoiskService.getTitle(id = id)

                    val responseBody = if (response.isSuccessful) response.body() else null

                    responseBody?.let { title ->
                        detailsData = DetailsData(
                            url = title.poster?.previewUrl,
                            name = title.name,
                            description = title.description,
                            episodesList = null
                        )

                        detailsDao.addTitle(
                            details = Details(
                                name = title.name ?: "",
                                description = title.description ?: "",
                                pictureUrl = title.poster?.previewUrl ?: "",
                                titleId = id,
                                category = API_CATEGORY_KINOPOISK,
                                lastUpdate = dateTime
                            )
                        )
                    }

                    lastTitleId = id

                    detailsData
                }
            }
        }
    }
}