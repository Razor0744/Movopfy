package com.example.movopfy.features.home.data.repository

import com.example.movopfy.common.mappers.anilibria.mapToAnimeSeriesList
import com.example.movopfy.common.models.AnimeSeries
import com.example.movopfy.database.dao.home.AnimeSeriesDao
import com.example.movopfy.database.models.home.RoomAnimeSeries
import com.example.movopfy.features.home.domain.repository.AnilibriaRepository
import com.example.movopfy.network.anilibria.service.AnilibriaService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AnilibriaRepositoryImpl(
    private val anilibriaService: AnilibriaService,
    private val animeSeriesDao: AnimeSeriesDao
) :
    AnilibriaRepository {

    private var animeSeriesList: List<AnimeSeries> = emptyList()

    override suspend fun getAnimeSeriesList(currentDay: Int): List<AnimeSeries> =
        withContext(Dispatchers.IO) {
            val localList = animeSeriesDao.getAnimeSeriesList(currentDay = currentDay)

            when {
                animeSeriesList.isNotEmpty() -> {
                    animeSeriesList
                }

                localList.isNotEmpty() -> {
                    animeSeriesList =
                        localList.map { AnimeSeries(id = it.id, pictureUrl = it.pictureUrl) }

                    animeSeriesList
                }

                else -> {
                    val response = anilibriaService.getSchedule()

                    val responseBody =
                        if (response.isSuccessful) response.body() else null

                    responseBody?.let {
                        animeSeriesList = mapToAnimeSeriesList(anilibriaSchedule = it[currentDay])

                        animeSeriesDao.addAnimeSeries(roomAnimeSeries = animeSeriesList.map { item ->
                            RoomAnimeSeries(
                                id = item.id,
                                pictureUrl = item.pictureUrl,
                                day = currentDay
                            )
                        }.toTypedArray())

                        animeSeriesList
                    } ?: emptyList()
                }
            }
        }
}