package com.example.movopfy.features.details.data.repository

import com.example.movopfy.common.constants.API_CATEGORY_ANILIBRIA
import com.example.movopfy.common.extensions.getSmallImageUrl
import com.example.movopfy.common.mappers.anilibria.mapToAnilibriaEpisodesList
import com.example.movopfy.database.dao.details.DetailsDao
import com.example.movopfy.database.models.details.Details
import com.example.movopfy.database.models.details.Episodes
import com.example.movopfy.features.details.domain.models.DetailsData
import com.example.movopfy.features.details.domain.repository.AnilibriaRepository
import com.example.movopfy.network.anilibria.models.AnilibriaEpisodesList
import com.example.movopfy.network.anilibria.service.AnilibriaService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

class AnilibriaRepositoryImpl(
    private val anilibriaService: AnilibriaService,
    private val detailsDao: DetailsDao
) : AnilibriaRepository {

    private val detailsDataMutex = Mutex()

    private var detailsData: DetailsData? = null
    private var lastTitleId: Int? = null

    override suspend fun getTitle(id: Int, dateTime: Int): DetailsData? =
        withContext(Dispatchers.IO) {
            detailsDataMutex.withLock {
                var localState =
                    if (detailsData == null || lastTitleId != id) detailsDao.getTitleById(
                        id = id,
                        category = API_CATEGORY_ANILIBRIA
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
                            episodesList = localState.episodesList.map {
                                AnilibriaEpisodesList(
                                    episode = it.episode,
                                    name = it.name,
                                    uuid = null,
                                    createdTimestamp = null,
                                    preview = null,
                                    hls = null
                                )
                            }
                        )

                        lastTitleId = id

                        detailsData
                    }

                    else -> {
                        val response = anilibriaService.getTitle(id = id)

                        val responseBody = if (response.isSuccessful) response.body() else null

                        responseBody?.let { title ->
                            detailsData = DetailsData(
                                url = title.getSmallImageUrl(),
                                name = title.anilibriaNames?.ru,
                                description = title.description,
                                episodesList = mapToAnilibriaEpisodesList(title.player?.list)
                            )

                            detailsDao.addTitle(
                                details = Details(
                                    name = title.anilibriaNames?.ru ?: "",
                                    description = title.description ?: "",
                                    pictureUrl = title.getSmallImageUrl() ?: "",
                                    titleId = id,
                                    category = API_CATEGORY_ANILIBRIA,
                                    lastUpdate = dateTime
                                )
                            )

                            detailsDao.addEpisodes(episodes = mapToAnilibriaEpisodesList(title.player?.list).map {
                                Episodes(
                                    name = it.name ?: "",
                                    episode = it.episode ?: 0,
                                    titleId = id
                                )
                            }.toTypedArray())
                        }

                        lastTitleId = id

                        detailsData
                    }
                }
            }
        }
}