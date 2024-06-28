package com.example.movopfy.features.home.domain.models

import com.example.common.models.AnimeSeries

data class HomeState(
    val animeSeriesList: List<com.example.common.models.AnimeSeries>,
    val movieList: MutableList<Pair<String, List<KinopoiskItem>>>
)
