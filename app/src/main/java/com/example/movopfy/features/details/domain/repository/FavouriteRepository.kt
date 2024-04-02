package com.example.movopfy.features.details.domain.repository

import com.example.movopfy.database.models.favourite.FavouriteModel

interface FavouriteRepository {

    suspend fun getFavouriteById(id: Int): FavouriteModel?

    suspend fun addToFavourite(favouriteModel: FavouriteModel)

    suspend fun removeFromFavourite(favouriteModel: FavouriteModel)
}