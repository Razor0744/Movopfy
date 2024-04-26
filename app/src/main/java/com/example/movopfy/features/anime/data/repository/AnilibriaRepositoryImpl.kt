package com.example.movopfy.features.anime.data.repository

import com.example.movopfy.common.constants.PreferencesKeys
import com.example.movopfy.common.mappers.anilibria.mapToAnimeSeriesList
import com.example.movopfy.common.models.AnimeSeries
import com.example.movopfy.dataStore.preferences.AppSettings
import com.example.movopfy.database.dao.anime.AnimeSchedulesDao
import com.example.movopfy.database.models.anime.AnimeSchedules
import com.example.movopfy.features.anime.domain.repository.AnilibriaRepository
import com.example.movopfy.network.anilibria.service.AnilibriaService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

const val DAYS_NUMBER = 6

class AnilibriaRepositoryImpl(
    private val anilibriaService: AnilibriaService,
    private val animeSchedulesDao: AnimeSchedulesDao,
    private val appSettings: AppSettings
) : AnilibriaRepository {

    private val listSchedulesMutex = Mutex()

    private var listSchedules: List<List<AnimeSeries>> = emptyList()

    override suspend fun getSchedules(dateTime: Int): List<List<AnimeSeries>> =
        withContext(Dispatchers.IO) {
            listSchedulesMutex.withLock {
                val date = appSettings.getInt(key = PreferencesKeys.ANIME_DATE)

                val localSchedules = when {
                    date != dateTime -> {
                        appSettings.setInt(key = PreferencesKeys.ANIME_DATE, value = dateTime)

                        emptyList()
                    }

                    listSchedules.isEmpty() -> {
                        animeSchedulesDao.getAnimeSchedules()
                    }

                    else -> emptyList()
                }

                when {
                    listSchedules.isNotEmpty() -> {
                        listSchedules
                    }

                    localSchedules.isNotEmpty() -> {
                        for (i in 0..DAYS_NUMBER) {
                            listSchedules += listOf(localSchedules.filter { it.day == i }
                                .map { item ->
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
                                it.map { item ->
                                    mapToAnimeSeriesList(anilibriaSchedule = item) ?: emptyList()
                                }

                            for (i in listSchedules) {
                                animeSchedulesDao.addAnimeSchedules(animeSchedules = i.map { item ->
                                    AnimeSchedules(
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
}