package com.example.movopfy.features.details.domain.repository

import com.example.movopfy.database.models.favourite.Favourite

interface FavouriteRepository {

    suspend fun getFavouriteById(id: Int): Favourite?

    suspend fun addToFavourite(favourite: Favourite)

    suspend fun removeFromFavourite(favourite: Favourite)
}