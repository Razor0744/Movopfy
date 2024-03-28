package com.example.movopfy.features.details.domain.repository

import com.example.movopfy.database.models.favourite.RoomFavourite

interface FavouriteRepository {

    suspend fun getFavouriteById(id: Int): RoomFavourite?

    suspend fun addToFavourite(roomFavourite: RoomFavourite)

    suspend fun removeFromFavourite(roomFavourite: RoomFavourite)
}