package com.example.movopfy.features.favourite.domain.repository

import com.example.database.models.favourite.FavouriteModel

interface FavouriteRepository {

    suspend fun getFavourites(): List<FavouriteModel>

    suspend fun removeFromFavourite(favouriteModel: FavouriteModel)
}