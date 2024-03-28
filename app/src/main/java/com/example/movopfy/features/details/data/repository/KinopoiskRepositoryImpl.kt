package com.example.movopfy.features.details.data.repository

import com.example.movopfy.common.constants.API_CATEGORY_KINOPOISK
import com.example.movopfy.database.dao.details.DetailsStateDao
import com.example.movopfy.database.models.details.RoomDetailsState
import com.example.movopfy.features.details.domain.models.DetailsState
import com.example.movopfy.features.details.domain.repository.KinopoiskRepository
import com.example.movopfy.network.kinopoisk.service.KinopoiskService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class KinopoiskRepositoryImpl(
    private val kinopoiskService: KinopoiskService,
    private val detailsStateDao: DetailsStateDao
) : KinopoiskRepository {

    private var state: DetailsState? = null
    private var idState: Int? = null

    override suspend fun getTitle(id: Int): DetailsState? = withContext(Dispatchers.IO) {
        val localState = detailsStateDao.getTitleById(id = id, category = API_CATEGORY_KINOPOISK)

        when {
            state != null && idState == id -> {
                state
            }

            localState != null -> {
                state = DetailsState(
                    url = localState.detailsState.pictureUrl,
                    name = localState.detailsState.name,
                    description = localState.detailsState.description,
                    episodesList = null
                )

                idState = id

                state
            }

            else -> {
                val response = kinopoiskService.getTitle(id = id)

                val responseBody = if (response.isSuccessful) response.body() else null

                responseBody?.let { title ->
                    state = DetailsState(
                        url = title.poster?.previewUrl,
                        name = title.name,
                        description = title.description,
                        episodesList = null
                    )

                    detailsStateDao.addTitle(
                        roomDetailsState = RoomDetailsState(
                            name = title.name ?: "",
                            description = title.description ?: "",
                            pictureUrl = title.poster?.previewUrl ?: "",
                            titleId = id,
                            category = API_CATEGORY_KINOPOISK
                        )
                    )
                }

                idState = id

                state
            }
        }
    }
}