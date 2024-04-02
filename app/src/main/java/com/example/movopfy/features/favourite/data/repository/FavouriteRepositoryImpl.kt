package com.example.movopfy.features.favourite.data.repository

import com.example.movopfy.database.dao.favorites.FavouriteDao
import com.example.movopfy.database.models.favourite.Favourite
import com.example.movopfy.features.favourite.domain.repository.FavouriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavouriteRepositoryImpl(private val favouriteDao: FavouriteDao) : FavouriteRepository {

    override suspend fun getFavourite(): List<Favourite> =
        withContext(Dispatchers.IO) {
            favouriteDao.getFavourite()
        }

    override suspend fun removeFromFavourite(favourite: Favourite) =
        withContext(Dispatchers.IO) {
            favouriteDao.removeFromFavourite(favourite = favourite)
        }
}