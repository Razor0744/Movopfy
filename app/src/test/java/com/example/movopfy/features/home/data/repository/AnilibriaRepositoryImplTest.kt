package com.example.movopfy.features.home.data.repository

import com.example.common.constants.PreferencesKeys
import com.example.common.mappers.anilibria.mapToAnimeSeriesList
import com.example.common.models.AnimeSeries
import com.example.database.dao.anime.AnimeSchedulesDao
import com.example.database.models.anime.AnimeSchedules
import com.example.datastore.preferences.AppSettings
import com.example.network.anilibria.models.AnilibriaSchedule
import com.example.network.anilibria.models.AnilibriaTitle
import com.example.network.anilibria.service.AnilibriaService
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import retrofit2.Response

class AnilibriaRepositoryImplTest {

    private val anilibriaService = mock<AnilibriaService>()
    private val animeSchedulesDao = mock<AnimeSchedulesDao>()
    private val appSettings = mock<AppSettings>()

    @Test
    fun shouldReturnLocalData() = runBlocking {
        val localData = arrayListOf(
            AnimeSchedules(
                id = 0,
                pictureUrl = "nice",
                day = 1
            )
        )

        val repositoryImpl = com.example.home.data.repository.AnilibriaRepositoryImpl(
            anilibriaService = anilibriaService,
            animeSchedulesDao = animeSchedulesDao,
            appSettings = appSettings
        )

        `when`(animeSchedulesDao.getAnimeListByDay(currentDay = anyInt())).thenReturn(localData)
        `when`(appSettings.getInt(key = PreferencesKeys.HOME_DATE)).thenReturn(1)

        val actual = repositoryImpl.getAnimeSeriesList(currentDay = 1, dateTime = 1)

        val expected = localData.map {
            AnimeSeries(
                id = it.id,
                pictureUrl = it.pictureUrl
            )
        }

        verify(animeSchedulesDao).getAnimeListByDay(currentDay = 1)
        verifyNoInteractions(anilibriaService)
        verify(appSettings).getInt(PreferencesKeys.HOME_DATE)

        assertEquals(expected, actual)
    }

    @Test
    fun shouldReturnEmptyList() = runBlocking {
        val responseData = listOf(
            AnilibriaSchedule(
                day = null,
                list = null
            )
        )

        val repositoryImpl = com.example.home.data.repository.AnilibriaRepositoryImpl(
            anilibriaService = anilibriaService,
            animeSchedulesDao = animeSchedulesDao,
            appSettings = appSettings
        )

        `when`(anilibriaService.getSchedule()).thenReturn(Response.success(responseData))
        `when`(appSettings.getInt(key = PreferencesKeys.HOME_DATE)).thenReturn(0)

        val actual = repositoryImpl.getAnimeSeriesList(currentDay = 0, dateTime = 1)

        val expected = emptyList<AnimeSeries>()

        verify(anilibriaService).getSchedule()
        verify(appSettings).getInt(PreferencesKeys.HOME_DATE)
        verify(animeSchedulesDao).addAnimeSchedules()

        assertEquals(expected, actual)
    }

    @Test
    fun shouldReturnResponseData() = runBlocking {
        val currentDay = 0

        val responseData = listOf(
            AnilibriaSchedule(
                day = 0, list = arrayListOf(
                    AnilibriaTitle(
                        id = null,
                        anilibriaNames = null,
                        anilibriaPosters = null,
                        player = null,
                        description = null
                    )
                )
            )
        )

        val repositoryImpl = com.example.home.data.repository.AnilibriaRepositoryImpl(
            anilibriaService = anilibriaService,
            animeSchedulesDao = animeSchedulesDao,
            appSettings = appSettings
        )

        `when`(animeSchedulesDao.getAnimeListByDay(currentDay = anyInt())).thenReturn(emptyList())
        `when`(anilibriaService.getSchedule()).thenReturn(Response.success(responseData))
        `when`(appSettings.getInt(key = PreferencesKeys.HOME_DATE)).thenReturn(0)

        val actual = repositoryImpl.getAnimeSeriesList(currentDay = currentDay, dateTime = 1)

        val expected =
            mapToAnimeSeriesList(anilibriaSchedule = responseData[currentDay])

        verify(anilibriaService).getSchedule()
        verify(appSettings).getInt(PreferencesKeys.HOME_DATE)

        assertEquals(expected, actual)
    }
}