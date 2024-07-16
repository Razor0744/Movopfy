package com.example.details.domain.repository

import com.example.database.models.favourite.FavouriteModel

interface FavouriteRepository {

    suspend fun getFavouriteById(id: Int): FavouriteModel?

    suspend fun addToFavourite(favouriteModel: FavouriteModel)

    suspend fun removeFromFavourite(favouriteModel: FavouriteModel)
}