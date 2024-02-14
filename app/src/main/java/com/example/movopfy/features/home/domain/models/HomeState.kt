package com.example.movopfy.features.home.domain.models

import com.example.movopfy.network.kinopoisk.models.KinopoiskDocs

data class HomeState(
    val animeSeriesList: List<AnimeSeries>,
    val movieList: MutableList<Pair<String, List<KinopoiskDocs>>>
)
