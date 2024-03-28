package com.example.movopfy.features.details.data.repository

import com.example.movopfy.common.constants.API_CATEGORY_ANILIBRIA
import com.example.movopfy.common.extensions.getSmallImageUrl
import com.example.movopfy.common.mappers.anilibria.mapToAnilibriaEpisodesList
import com.example.movopfy.database.dao.details.DetailsStateDao
import com.example.movopfy.database.models.details.RoomDetailsState
import com.example.movopfy.database.models.details.RoomEpisodes
import com.example.movopfy.features.details.domain.models.DetailsState
import com.example.movopfy.features.details.domain.repository.AnilibriaRepository
import com.example.movopfy.network.anilibria.models.AnilibriaEpisodesList
import com.example.movopfy.network.anilibria.service.AnilibriaService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AnilibriaRepositoryImpl(
    private val anilibriaService: AnilibriaService,
    private val detailsStateDao: DetailsStateDao
) : AnilibriaRepository {

    private var state: DetailsState? = null
    private var idState: Int? = null

    override suspend fun getTitle(id: Int): DetailsState? = withContext(Dispatchers.IO) {
        val localState = detailsStateDao.getTitleById(id = id, category = API_CATEGORY_ANILIBRIA)

        when {
            state != null && idState == id -> {
                state
            }

            localState != null -> {
                state = DetailsState(
                    url = localState.detailsState.pictureUrl,
                    name = localState.detailsState.name,
                    description = localState.detailsState.description,
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

                idState = id

                state
            }

            else -> {
                val response = anilibriaService.getTitle(id = id)

                val responseBody = if (response.isSuccessful) response.body() else null

                responseBody?.let { title ->
                    state = DetailsState(
                        url = title.getSmallImageUrl(),
                        name = title.anilibriaNames?.ru,
                        description = title.description,
                        episodesList = mapToAnilibriaEpisodesList(title.player?.list)
                    )

                    detailsStateDao.addTitle(
                        roomDetailsState = RoomDetailsState(
                            name = title.anilibriaNames?.ru ?: "",
                            description = title.description ?: "",
                            pictureUrl = title.getSmallImageUrl() ?: "",
                            titleId = id,
                            category = API_CATEGORY_ANILIBRIA
                        )
                    )

                    detailsStateDao.addEpisodes(roomEpisodes = mapToAnilibriaEpisodesList(title.player?.list).map {
                        RoomEpisodes(
                            name = it.name ?: "",
                            episode = it.episode ?: 0,
                            titleId = id
                        )
                    }.toTypedArray())
                }

                idState = id

                state
            }
        }
    }
}