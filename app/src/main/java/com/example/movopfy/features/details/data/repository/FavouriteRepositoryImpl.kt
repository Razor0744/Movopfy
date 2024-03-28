package com.example.movopfy.features.details.data.repository

import com.example.movopfy.database.dao.favorites.FavouriteDao
import com.example.movopfy.database.models.favourite.RoomFavourite
import com.example.movopfy.features.details.domain.repository.FavouriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavouriteRepositoryImpl(private val favouriteDao: FavouriteDao) : FavouriteRepository {

    override suspend fun getFavouriteById(id: Int): RoomFavourite? =
        withContext(Dispatchers.IO) {
            favouriteDao.getFavouriteById(id = id)
        }

    override suspend fun addToFavourite(roomFavourite: RoomFavourite) =
        withContext(Dispatchers.IO) {
            favouriteDao.addToFavourite(roomFavourite = roomFavourite)
        }

    override suspend fun removeFromFavourite(roomFavourite: RoomFavourite) =
        withContext(Dispatchers.IO) {
            favouriteDao.removeFromFavourite(roomFavourite = roomFavourite)
        }
}