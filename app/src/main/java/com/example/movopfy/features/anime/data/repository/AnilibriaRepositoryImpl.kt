package com.example.movopfy.features.anime.data.repository

import com.example.movopfy.common.mappers.anilibria.mapToAnimeSeriesList
import com.example.movopfy.common.models.AnimeSeries
import com.example.movopfy.database.dao.anime.AnimeSchedulesDao
import com.example.movopfy.database.models.anime.RoomAnimeSchedules
import com.example.movopfy.features.anime.domain.repository.AnilibriaRepository
import com.example.movopfy.network.anilibria.service.AnilibriaService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AnilibriaRepositoryImpl(
    private val anilibriaService: AnilibriaService,
    private val animeSchedulesDao: AnimeSchedulesDao
) : AnilibriaRepository {

    private var listSchedules: List<List<AnimeSeries>> = emptyList()

    override suspend fun getSchedules(): List<List<AnimeSeries>> = withContext(Dispatchers.IO) {
        val localSchedules = animeSchedulesDao.getAnimeSchedules()

        when {
            listSchedules.isNotEmpty() -> {
                listSchedules
            }

            localSchedules.isNotEmpty() -> {
                for (i in 0..6) {
                    listSchedules += listOf(localSchedules.filter { it.day == i }.map { item ->
                        AnimeSeries(id = item.id, pictureUrl = item.pictureUrl)
                    })
                }

                listSchedules
            }

            else -> {
                val response = anilibriaService.getSchedule()

                val responseBody = if (response.isSuccessful) response.body() else null

                responseBody?.let {
                    listSchedules =
                        it.map { item -> mapToAnimeSeriesList(anilibriaSchedule = item) }

                    for (i in listSchedules) {
                        animeSchedulesDao.addAnimeSchedules(roomAnimeSchedules = i.map { item ->
                            RoomAnimeSchedules(
                                id = item.id,
                                pictureUrl = item.pictureUrl,
                                day = listSchedules.indexOf(i)
                            )
                        }.toTypedArray())
                    }
                }

                listSchedules
            }
        }
    }
}