package com.example.movopfy.features.favourite.data.repository

import com.example.movopfy.database.dao.favorites.FavouriteDao
import com.example.movopfy.database.models.favourite.RoomFavourite
import com.example.movopfy.features.favourite.domain.repository.FavouriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavouriteRepositoryImpl(private val favouriteDao: FavouriteDao) : FavouriteRepository {

    override suspend fun getFavourite(): List<RoomFavourite> =
        withContext(Dispatchers.IO) {
            favouriteDao.getFavourite()
        }

    override suspend fun removeFromFavourite(roomFavourite: RoomFavourite) =
        withContext(Dispatchers.IO) {
            favouriteDao.removeFromFavourite(roomFavourite = roomFavourite)
        }
}