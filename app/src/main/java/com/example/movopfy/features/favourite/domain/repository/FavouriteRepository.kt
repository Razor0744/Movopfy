package com.example.movopfy.features.favourite.domain.repository

import com.example.movopfy.database.models.favourite.Favourite

interface FavouriteRepository {

    suspend fun getFavourite(): List<Favourite>

    suspend fun removeFromFavourite(favourite: Favourite)
}