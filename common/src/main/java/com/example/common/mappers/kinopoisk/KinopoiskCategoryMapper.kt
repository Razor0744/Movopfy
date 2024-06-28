package com.example.common.mappers.kinopoisk


const val HORRORS = "Ужасы"
const val COMEDY = "Комедия"
const val DRAMA = "Драма"
const val MELODRAMA = "Мелодрама"
const val HORRORS_CATEGORY = "+ужасы"
const val COMEDY_CATEGORY = "комедия"
const val DRAMA_CATEGORY = "драма"
const val MELODRAMA_CATEGORY = "!мелодрама"

fun mapToKinopoiskCategory(category: String) = when (category) {
    HORRORS -> HORRORS_CATEGORY
    COMEDY -> COMEDY_CATEGORY
    DRAMA -> DRAMA_CATEGORY
    MELODRAMA -> MELODRAMA_CATEGORY
    else -> HORRORS_CATEGORY
}