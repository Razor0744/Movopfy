package com.example.movopfy.features.anime.data

import com.example.common.constants.PreferencesKeys
import com.example.common.mappers.anilibria.mapToAnimeSeriesList
import com.example.common.models.AnimeSeries
import com.example.datastore.preferences.AppSettings
import com.example.database.dao.anime.AnimeSchedulesDao
import com.example.database.models.anime.AnimeSchedules
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

    private val anilibriaService = mock<AnilibriaService>()
    private val animeSchedulesDao = mock<AnimeSchedulesDao>()
    private val appSettings = mock<AppSettings>()

    @Test
    fun shouldReturnLocalData() = runBlocking {
        val localData = listOf(
            AnimeSchedules(
                id = 1,
                pictureUrl = "",
                day = 1
            )
        )

        val repositoryImpl = AnilibriaRepositoryImpl(
            anilibriaService = anilibriaService,
            animeSchedulesDao = animeSchedulesDao,
            appSettings = appSettings
        )

        `when`(animeSchedulesDao.getAnimeSchedules()).thenReturn(localData)
        `when`(appSettings.getInt(key = PreferencesKeys.ANIME_DATE)).thenReturn(1)

        val actual = repositoryImpl.getSchedules(dateTime = 1)

        val expected = mutableListOf<List<AnimeSeries>>()
        for (i in 0..DAYS_NUMBER) {
            expected += listOf(localData.filter { it.day == i }.map { item ->
                AnimeSeries(id = item.id, pictureUrl = item.pictureUrl)
            })
        }

        verify(animeSchedulesDao).getAnimeSchedules()
        verifyNoInteractions(anilibriaService)
        verify(appSettings).getInt(PreferencesKeys.ANIME_DATE)

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

        val repositoryImpl = AnilibriaRepositoryImpl(
            anilibriaService = anilibriaService,
            animeSchedulesDao = animeSchedulesDao,
            appSettings = appSettings
        )

        `when`(animeSchedulesDao.getAnimeSchedules()).thenReturn(emptyList())
        `when`(anilibriaService.getSchedule()).thenReturn(Response.success(responseData))
        `when`(appSettings.getInt(key = PreferencesKeys.ANIME_DATE)).thenReturn(0)

        val actual = repositoryImpl.getSchedules(dateTime = 1)

        val expected = listOf(listOf<AnimeSeries>())

        verify(anilibriaService).getSchedule()
        verify(appSettings).getInt(PreferencesKeys.ANIME_DATE)
        verify(appSettings).setInt(key = PreferencesKeys.ANIME_DATE, value = 1)

        assertEquals(expected, actual)
    }

    @Test
    fun shouldReturnResponseData() = runBlocking {
        val responseData = listOf(
            AnilibriaSchedule(
                day = 1, list = listOf(
                    AnilibriaTitle(
                        id = 52,
                        anilibriaNames = AnilibriaNames(ru = "Held"),
                        anilibriaPosters = AnilibriaPosters(
                            small = PostersSmall(
                                url = "small",
                                null
                            ),
                            medium = PostersMedium(
                                url = "medium",
                                null
                            ),
                            original = PostersOriginal(
                                url = "original",
                                null
                            )
                        ),
                        player = AnilibriaPlayer(
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
        `when`(appSettings.getInt(key = PreferencesKeys.ANIME_DATE)).thenReturn(0)

        val actual = repositoryImpl.getSchedules(dateTime = 1)

        val expected = responseData.map { item ->
            mapToAnimeSeriesList(anilibriaSchedule = item) ?: emptyList()
        }

        verify(anilibriaService).getSchedule()
        verify(appSettings).getInt(key = PreferencesKeys.ANIME_DATE)
        verify(appSettings).setInt(key = PreferencesKeys.ANIME_DATE, value = 1)

        assertEquals(expected, actual)
    }
}