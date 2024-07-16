package com.example.movopfy.features.home.domain.usecase

import com.example.common.models.AnimeSeries
import com.example.home.domain.models.HomeState
import com.example.home.domain.models.KinopoiskItem
import com.example.home.domain.repository.AnilibriaRepository
import com.example.home.domain.repository.KinopoiskRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class GetHomeDataUseCaseTest {

    private val anilibriaRepository = mock<com.example.home.domain.repository.AnilibriaRepository>()
    private val kinopoiskRepository = mock<com.example.home.domain.repository.KinopoiskRepository>()

    @Test
    fun shouldReturnHomeState() = runBlocking {
        val animeList = listOf<com.example.common.models.AnimeSeries>()
        val horrorList = listOf<com.example.home.domain.models.KinopoiskItem>()
        val comedyList = listOf<com.example.home.domain.models.KinopoiskItem>()
        val dramaList = listOf<com.example.home.domain.models.KinopoiskItem>()
        val melodramaList = listOf<com.example.home.domain.models.KinopoiskItem>()

        `when`(anilibriaRepository.getAnimeSeriesList(anyInt(), anyInt())).thenReturn(animeList)
        `when`(kinopoiskRepository.getList(anyInt(), anyString()))
            .thenReturn(horrorList)
            .thenReturn(comedyList)
            .thenReturn(dramaList)
            .thenReturn(melodramaList)

        val useCase = com.example.home.domain.usecase.GetHomeDataUseCase(
            anilibriaRepository,
            kinopoiskRepository
        )

        val actual = useCase.execute(currentDay = 1, dateTime = 1)

        val expected = com.example.home.domain.models.HomeState(
            animeSeriesList = animeList,
            movieList = arrayListOf(
                Pair(com.example.home.domain.usecase.HORRORS, horrorList),
                Pair(com.example.home.domain.usecase.COMEDY, comedyList),
                Pair(com.example.home.domain.usecase.DRAMA, dramaList),
                Pair(com.example.home.domain.usecase.MELODRAMA, melodramaList)
            )
        )

        verify(anilibriaRepository).getAnimeSeriesList(currentDay = 1, dateTime = 1)
        verify(kinopoiskRepository).getList(
            com.example.home.domain.usecase.KINOPOISK_PAGE,
            com.example.home.domain.usecase.HORRORS_CATEGORY
        )
        verify(kinopoiskRepository).getList(
            com.example.home.domain.usecase.KINOPOISK_PAGE,
            com.example.home.domain.usecase.COMEDY_CATEGORY
        )
        verify(kinopoiskRepository).getList(
            com.example.home.domain.usecase.KINOPOISK_PAGE,
            com.example.home.domain.usecase.DRAMA_CATEGORY
        )
        verify(kinopoiskRepository).getList(
            com.example.home.domain.usecase.KINOPOISK_PAGE,
            com.example.home.domain.usecase.MELODRAMA_CATEGORY
        )

        assertEquals(expected, actual)
    }
}