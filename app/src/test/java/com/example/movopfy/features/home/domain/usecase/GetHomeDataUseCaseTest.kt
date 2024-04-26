package com.example.movopfy.features.home.domain.usecase

import com.example.movopfy.common.models.AnimeSeries
import com.example.movopfy.features.home.domain.models.HomeState
import com.example.movopfy.features.home.domain.models.KinopoiskItem
import com.example.movopfy.features.home.domain.repository.AnilibriaRepository
import com.example.movopfy.features.home.domain.repository.KinopoiskRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class GetHomeDataUseCaseTest {

    private val anilibriaRepository = mock<AnilibriaRepository>()
    private val kinopoiskRepository = mock<KinopoiskRepository>()

    @Test
    fun shouldReturnHomeState() = runBlocking {
        val animeList = listOf<AnimeSeries>()
        val horrorList = listOf<KinopoiskItem>()
        val comedyList = listOf<KinopoiskItem>()
        val dramaList = listOf<KinopoiskItem>()
        val melodramaList = listOf<KinopoiskItem>()

        `when`(anilibriaRepository.getAnimeSeriesList(anyInt(), anyInt())).thenReturn(animeList)
        `when`(kinopoiskRepository.getList(anyInt(), anyString()))
            .thenReturn(horrorList)
            .thenReturn(comedyList)
            .thenReturn(dramaList)
            .thenReturn(melodramaList)

        val useCase = GetHomeDataUseCase(anilibriaRepository, kinopoiskRepository)

        val actual = useCase.execute(currentDay = 1, dateTime = 1)

        val expected = HomeState(
            animeSeriesList = animeList,
            movieList = arrayListOf(
                Pair(HORRORS, horrorList),
                Pair(COMEDY, comedyList),
                Pair(DRAMA, dramaList),
                Pair(MELODRAMA, melodramaList)
            )
        )

        verify(anilibriaRepository).getAnimeSeriesList(currentDay = 1, dateTime = 1)
        verify(kinopoiskRepository).getList(KINOPOISK_PAGE, HORRORS_CATEGORY)
        verify(kinopoiskRepository).getList(KINOPOISK_PAGE, COMEDY_CATEGORY)
        verify(kinopoiskRepository).getList(KINOPOISK_PAGE, DRAMA_CATEGORY)
        verify(kinopoiskRepository).getList(KINOPOISK_PAGE, MELODRAMA_CATEGORY)

        assertEquals(expected, actual)
    }
}