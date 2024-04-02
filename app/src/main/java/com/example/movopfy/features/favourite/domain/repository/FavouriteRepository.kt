package com.example.movopfy.features.favourite.domain.repository

import com.example.movopfy.database.models.favourite.FavouriteModel

interface FavouriteRepository {

    suspend fun getFavourite(): List<FavouriteModel>

    suspend fun removeFromFavourite(favouriteModel: FavouriteModel)
}