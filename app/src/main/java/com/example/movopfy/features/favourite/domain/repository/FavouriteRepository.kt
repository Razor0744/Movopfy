package com.example.movopfy.features.favourite.domain.repository

import com.example.movopfy.database.models.favourite.RoomFavourite

interface FavouriteRepository {

    suspend fun getFavourite(): List<RoomFavourite>

    suspend fun removeFromFavourite(roomFavourite: RoomFavourite)
}