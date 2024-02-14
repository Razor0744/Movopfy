package com.example.movopfy.features.home.domain.usecase

import com.example.movopfy.common.mappers.mapAnilibriaUrlToImageUrl
import com.example.movopfy.features.home.domain.models.HomeState
import com.example.movopfy.features.home.domain.repository.AnilibriaRepository
import com.example.movopfy.features.home.domain.repository.KinopoiskRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

const val HORRORS = "Ужасы"
const val COMEDY = "Комедия"
const val DRAMA = "Драма"
const val MELODRAMA = "Мелодрама"
const val HORRORS_CATEGORY = "+ужасы"
const val COMEDY_CATEGORY = "комедия"
const val DRAMA_CATEGORY = "драма"
const val MELODRAMA_CATEGORY = "!мелодрама"
const val KINOPOISK_PAGE = 1

class GetDataUseCase(
    private val anilibriaRepository: AnilibriaRepository,
    private val kinopoiskRepository: KinopoiskRepository
) {

    suspend fun execute(currentDay: Int): HomeState = coroutineScope {
        val animeList =
            async { mapAnilibriaUrlToImageUrl(anilibriaSchedule = anilibriaRepository.getSchedule()[currentDay]) }

        val horrorList = async {
            Pair(
                HORRORS,
                kinopoiskRepository.getList(page = KINOPOISK_PAGE, category = HORRORS_CATEGORY)
            )
        }
        val comedyList = async {
            Pair(
                COMEDY,
                kinopoiskRepository.getList(page = KINOPOISK_PAGE, category = COMEDY_CATEGORY)
            )
        }
        val dramaList = async {
            Pair(
                DRAMA,
                kinopoiskRepository.getList(page = KINOPOISK_PAGE, category = DRAMA_CATEGORY)
            )
        }
        val melodrama = async {
            Pair(
                MELODRAMA,
                kinopoiskRepository.getList(
                    page = KINOPOISK_PAGE,
                    category = MELODRAMA_CATEGORY
                )
            )
        }

        HomeState(
            animeSeriesList = animeList.await(),
            movieList = arrayListOf(
                horrorList.await(),
                comedyList.await(),
                dramaList.await(),
                melodrama.await()
            )
        )
    }
}