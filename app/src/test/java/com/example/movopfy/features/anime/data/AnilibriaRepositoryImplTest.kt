package com.example.movopfy.features.anime.data

import com.example.common.constants.PreferencesKeys
import com.example.common.mappers.anilibria.mapToAnimeSeriesList
import com.example.common.models.AnimeSeries
import com.example.movopfy.datastore.preferences.AppSettings
import com.example.movopfy.database.dao.anime.AnimeSchedulesDao
import com.example.movopfy.database.models.anime.AnimeSchedules
import com.example.movopfy.features.anime.data.repository.AnilibriaRepositoryImpl
import com.example.movopfy.features.anime.data.repository.DAYS_NUMBER
import com.example.network.anilibria.models.AnilibriaNames
import com.example.network.anilibria.models.AnilibriaPlayer
import com.example.network.anilibria.models.AnilibriaPosters
import com.example.network.anilibria.models.AnilibriaSchedule
import com.example.network.anilibria.models.AnilibriaTitle
import com.example.network.anilibria.models.PostersMedium
import com.example.network.anilibria.models.PostersOriginal
import com.example.network.anilibria.models.PostersSmall
import com.example.network.anilibria.service.AnilibriaService
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import retrofit2.Response

class AnilibriaRepositoryImplTest {

    private val anilibriaService = mock<com.example.network.anilibria.service.AnilibriaService>()
    private val animeSchedulesDao = mock<AnimeSchedulesDao>()
    private val appSettings = mock<AppSettings>()

    @Test
    fun shouldReturnLocalData() = runBlocking {
        val localData = listOf(AnimeSchedules(id = 1, pictureUrl = "", day = 1))

        val repositoryImpl = AnilibriaRepositoryImpl(
            anilibriaService = anilibriaService,
            animeSchedulesDao = animeSchedulesDao,
            appSettings = appSettings
        )

        `when`(animeSchedulesDao.getAnimeSchedules()).thenReturn(localData)
        `when`(appSettings.getInt(key = com.example.common.constants.PreferencesKeys.ANIME_DATE)).thenReturn(1)

        val actual = repositoryImpl.getSchedules(dateTime = 1)

        val expected = mutableListOf<List<com.example.common.models.AnimeSeries>>()
        for (i in 0..DAYS_NUMBER) {
            expected += listOf(localData.filter { it.day == i }.map { item ->
                com.example.common.models.AnimeSeries(id = item.id, pictureUrl = item.pictureUrl)
            })
        }

        verify(animeSchedulesDao).getAnimeSchedules()
        verifyNoInteractions(anilibriaService)
        verify(appSettings).getInt(com.example.common.constants.PreferencesKeys.ANIME_DATE)

        assertEquals(expected, actual)
    }

    @Test
    fun shouldReturnEmptyList() = runBlocking {
        val responseData = listOf(
            com.example.network.anilibria.models.AnilibriaSchedule(
                day = null,
                list = null
            )
        )

        val repositoryImpl = AnilibriaRepositoryImpl(
            anilibriaService = anilibriaService,
            animeSchedulesDao = animeSchedulesDao,
            appSettings = appSettings
        )

        `when`(animeSchedulesDao.getAnimeSchedules()).thenReturn(emptyList())
        `when`(anilibriaService.getSchedule()).thenReturn(Response.success(responseData))
        `when`(appSettings.getInt(key = com.example.common.constants.PreferencesKeys.ANIME_DATE)).thenReturn(0)

        val actual = repositoryImpl.getSchedules(dateTime = 1)

        val expected = listOf(listOf<com.example.common.models.AnimeSeries>())

        verify(anilibriaService).getSchedule()
        verify(appSettings).getInt(com.example.common.constants.PreferencesKeys.ANIME_DATE)
        verify(appSettings).setInt(key = com.example.common.constants.PreferencesKeys.ANIME_DATE, value = 1)

        assertEquals(expected, actual)
    }

    @Test
    fun shouldReturnResponseData() = runBlocking {
        val responseData = listOf(
            com.example.network.anilibria.models.AnilibriaSchedule(
                day = 1, list = listOf(
                    com.example.network.anilibria.models.AnilibriaTitle(
                        id = 52,
                        anilibriaNames = com.example.network.anilibria.models.AnilibriaNames(ru = "Held"),
                        anilibriaPosters = com.example.network.anilibria.models.AnilibriaPosters(
                            small = com.example.network.anilibria.models.PostersSmall(
                                url = "small",
                                null
                            ),
                            medium = com.example.network.anilibria.models.PostersMedium(
                                url = "medium",
                                null
                            ),
                            original = com.example.network.anilibria.models.PostersOriginal(
                                url = "original",
                                null
                            )
                        ),
                        player = com.example.network.anilibria.models.AnilibriaPlayer(
                            episodes = null,
                            list = null
                        ),
                        description = "description for title"
                    )
                )
            )
        )

        val repositoryImpl = AnilibriaRepositoryImpl(
            anilibriaService = anilibriaService,
            animeSchedulesDao = animeSchedulesDao,
            appSettings = appSettings
        )

        `when`(animeSchedulesDao.getAnimeSchedules()).thenReturn(emptyList())
        `when`(anilibriaService.getSchedule()).thenReturn(Response.success(responseData))
        `when`(appSettings.getInt(key = com.example.common.constants.PreferencesKeys.ANIME_DATE)).thenReturn(0)

        val actual = repositoryImpl.getSchedules(dateTime = 1)

        val expected = responseData.map { item ->
            com.example.common.mappers.anilibria.mapToAnimeSeriesList(anilibriaSchedule = item) ?: emptyList()
        }

        verify(anilibriaService).getSchedule()
        verify(appSettings).getInt(key = com.example.common.constants.PreferencesKeys.ANIME_DATE)
        verify(appSettings).setInt(key = com.example.common.constants.PreferencesKeys.ANIME_DATE, value = 1)

        assertEquals(expected, actual)
    }
}