package com.example.movopfy.features.home.domain.models

import com.example.movopfy.common.models.AnimeSeries

data class HomeState(
    val animeSeriesList: List<AnimeSeries>,
    val movieList: MutableList<Pair<String, List<KinopoiskItem>>>
)
