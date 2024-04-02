package com.example.movopfy.features.favourite.domain.models

import com.example.movopfy.database.models.favourite.FavouriteModel

data class FavouriteState(
    val list: List<FavouriteModel>
)
