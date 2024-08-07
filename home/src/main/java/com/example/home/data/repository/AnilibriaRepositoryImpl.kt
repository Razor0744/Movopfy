package com.example.home.data.repository

import com.example.common.constants.PreferencesKeys
import com.example.common.mappers.anilibria.mapToAnimeSeriesList
import com.example.common.models.AnimeSeries
import com.example.database.dao.anime.AnimeSchedulesDao
import com.example.database.models.anime.AnimeSchedules
import com.example.datastore.preferences.AppSettings
import com.example.home.domain.repository.AnilibriaRepository
import com.example.network.anilibria.service.AnilibriaService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

class AnilibriaRepositoryImpl(
    private val anilibriaService: AnilibriaService,
    private val animeSchedulesDao: AnimeSchedulesDao,
    private val appSettings: AppSettings
) : AnilibriaRepository {

    private val animeSeriesListMutex = Mutex()

    private var animeSeriesList: List<AnimeSeries> = emptyList()

    override suspend fun getAnimeSeriesList(currentDay: Int, dateTime: Int): List<AnimeSeries> =
        withContext(Dispatchers.IO) {
            animeSeriesListMutex.withLock {
                val date = appSettings.getInt(key = PreferencesKeys.HOME_DATE)

                val localList = when {
                    date != dateTime -> {
                        appSettings.setInt(key = PreferencesKeys.HOME_DATE, value = dateTime)

                        emptyList()
                    }

                    animeSeriesList.isEmpty() -> {
                        animeSchedulesDao.getAnimeListByDay(currentDay = currentDay)
                    }

                    else -> emptyList()
                }

                when {
                    animeSeriesList.isNotEmpty() -> {
                        animeSeriesList
                    }

                    localList.isNotEmpty() -> {
                        animeSeriesList =
                            localList.map {
                                AnimeSeries(
                                    id = it.id,
                                    pictureUrl = it.pictureUrl
                                )
                            }

                        animeSeriesList
                    }

                    else -> {
                        val response = anilibriaService.getSchedule()

                        val responseBody =
                            if (response.isSuccessful) response.body() else null

                        responseBody?.let {
                            animeSeriesList =
                                mapToAnimeSeriesList(
                                    anilibriaSchedule = it[currentDay]
                                )
                                    ?: emptyList()

                            animeSchedulesDao.addAnimeSchedules(animeSchedules = animeSeriesList.map { item ->
                                AnimeSchedules(
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
}