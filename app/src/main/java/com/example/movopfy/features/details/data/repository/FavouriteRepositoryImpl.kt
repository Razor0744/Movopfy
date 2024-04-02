package com.example.movopfy.features.details.data.repository

import com.example.movopfy.database.dao.favorites.FavouriteDao
import com.example.movopfy.database.models.favourite.Favourite
import com.example.movopfy.features.details.domain.repository.FavouriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavouriteRepositoryImpl(private val favouriteDao: FavouriteDao) : FavouriteRepository {

    override suspend fun getFavouriteById(id: Int): Favourite? =
        withContext(Dispatchers.IO) {
            favouriteDao.getFavouriteById(id = id)
        }

    override suspend fun addToFavourite(favourite: Favourite) =
        withContext(Dispatchers.IO) {
            favouriteDao.addToFavourite(favourite = favourite)
        }

    override suspend fun removeFromFavourite(favourite: Favourite) =
        withContext(Dispatchers.IO) {
            favouriteDao.removeFromFavourite(favourite = favourite)
        }
}