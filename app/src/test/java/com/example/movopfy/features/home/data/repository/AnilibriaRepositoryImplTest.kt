package com.example.movopfy.features.home.data.repository

import com.example.movopfy.common.mappers.anilibria.mapToAnimeSeriesList
import com.example.movopfy.common.models.AnimeSeries
import com.example.movopfy.database.dao.home.AnimeSeriesDao
import com.example.movopfy.database.models.home.Anime
import com.example.movopfy.network.anilibria.models.AnilibriaSchedule
import com.example.movopfy.network.anilibria.models.AnilibriaTitle
import com.example.movopfy.network.anilibria.service.AnilibriaService
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
    private val animeSeriesDao = mock<AnimeSeriesDao>()

    @Test
    fun shouldReturnLocalData() = runBlocking {
        val localData = arrayListOf(Anime(id = 0, pictureUrl = "nice", day = 1))

        val repositoryImpl = AnilibriaRepositoryImpl(
            anilibriaService = anilibriaService,
            animeSeriesDao = animeSeriesDao
        )

        `when`(animeSeriesDao.getAnimeSeriesList(currentDay = anyInt())).thenReturn(localData)

        val actual = repositoryImpl.getAnimeSeriesList(currentDay = 1)

        val expected = localData.map { AnimeSeries(id = it.id, pictureUrl = it.pictureUrl) }

        verify(animeSeriesDao).getAnimeSeriesList(currentDay = 1)
        verifyNoInteractions(anilibriaService)

        assertEquals(expected, actual)
    }

    @Test
    fun shouldReturnEmptyList() = runBlocking {
        val responseData = listOf(AnilibriaSchedule(day = null, list = null))

        val repositoryImpl = AnilibriaRepositoryImpl(
            anilibriaService = anilibriaService,
            animeSeriesDao = animeSeriesDao
        )

        `when`(animeSeriesDao.getAnimeSeriesList(currentDay = anyInt())).thenReturn(emptyList())
        `when`(anilibriaService.getSchedule()).thenReturn(Response.success(responseData))

        val actual = repositoryImpl.getAnimeSeriesList(currentDay = 0)

        val expected = emptyList<AnimeSeries>()

        verify(animeSeriesDao).getAnimeSeriesList(currentDay = 0)
        verify(anilibriaService).getSchedule()

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

        val repositoryImpl = AnilibriaRepositoryImpl(
            anilibriaService = anilibriaService,
            animeSeriesDao = animeSeriesDao
        )

        `when`(animeSeriesDao.getAnimeSeriesList(currentDay = anyInt())).thenReturn(emptyList())
        `when`(anilibriaService.getSchedule()).thenReturn(Response.success(responseData))

        val actual = repositoryImpl.getAnimeSeriesList(currentDay = currentDay)

        val expected = mapToAnimeSeriesList(anilibriaSchedule = responseData[currentDay])

        verify(animeSeriesDao).getAnimeSeriesList(currentDay = currentDay)
        verify(anilibriaService).getSchedule()

        assertEquals(expected, actual)
    }
}