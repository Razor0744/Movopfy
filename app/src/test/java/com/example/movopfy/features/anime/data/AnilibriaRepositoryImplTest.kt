package com.example.movopfy.features.anime.data

import com.example.movopfy.common.mappers.anilibria.mapToAnimeSeriesList
import com.example.movopfy.common.models.AnimeSeries
import com.example.movopfy.database.dao.anime.AnimeSchedulesDao
import com.example.movopfy.database.models.anime.AnimeSchedules
import com.example.movopfy.features.anime.data.repository.AnilibriaRepositoryImpl
import com.example.movopfy.features.anime.data.repository.DAYS_NUMBER
import com.example.movopfy.network.anilibria.models.AnilibriaNames
import com.example.movopfy.network.anilibria.models.AnilibriaPlayer
import com.example.movopfy.network.anilibria.models.AnilibriaPosters
import com.example.movopfy.network.anilibria.models.AnilibriaSchedule
import com.example.movopfy.network.anilibria.models.AnilibriaTitle
import com.example.movopfy.network.anilibria.models.PostersMedium
import com.example.movopfy.network.anilibria.models.PostersOriginal
import com.example.movopfy.network.anilibria.models.PostersSmall
import com.example.movopfy.network.anilibria.service.AnilibriaService
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

    @Test
    fun shouldReturnLocalData() = runBlocking {
        val localData = listOf(AnimeSchedules(id = 1, pictureUrl = "", day = 1))

        val repositoryImpl = AnilibriaRepositoryImpl(
            anilibriaService = anilibriaService,
            animeSchedulesDao = animeSchedulesDao
        )

        `when`(animeSchedulesDao.getAnimeSchedules()).thenReturn(localData)

        val actual = repositoryImpl.getSchedules()

        val expected = mutableListOf<List<AnimeSeries>>()
        for (i in 0..DAYS_NUMBER) {
            expected += listOf(localData.filter { it.day == i }.map { item ->
                AnimeSeries(id = item.id, pictureUrl = item.pictureUrl)
            })
        }

        verify(animeSchedulesDao).getAnimeSchedules()
        verifyNoInteractions(anilibriaService)

        assertEquals(expected, actual)
    }

    @Test
    fun shouldReturnEmptyList() = runBlocking {
        val responseData = listOf(AnilibriaSchedule(day = null, list = null))

        val repositoryImpl = AnilibriaRepositoryImpl(
            anilibriaService = anilibriaService,
            animeSchedulesDao = animeSchedulesDao
        )

        `when`(animeSchedulesDao.getAnimeSchedules()).thenReturn(emptyList())
        `when`(anilibriaService.getSchedule()).thenReturn(Response.success(responseData))

        val actual = repositoryImpl.getSchedules()

        val expected = listOf(listOf<AnimeSeries>())

        verify(animeSchedulesDao).getAnimeSchedules()
        verify(anilibriaService).getSchedule()

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
                            small = PostersSmall(url = "small", null),
                            medium = PostersMedium(url = "medium", null),
                            original = PostersOriginal(url = "original", null)
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
            animeSchedulesDao = animeSchedulesDao
        )

        `when`(animeSchedulesDao.getAnimeSchedules()).thenReturn(emptyList())
        `when`(anilibriaService.getSchedule()).thenReturn(Response.success(responseData))

        val actual = repositoryImpl.getSchedules()

        val expected = responseData.map { item ->
            mapToAnimeSeriesList(anilibriaSchedule = item) ?: emptyList()
        }

        verify(animeSchedulesDao).getAnimeSchedules()
        verify(anilibriaService).getSchedule()

        assertEquals(expected, actual)
    }
}