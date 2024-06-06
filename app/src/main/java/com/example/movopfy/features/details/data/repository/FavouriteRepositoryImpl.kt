package com.example.movopfy.features.details.data.repository

import com.example.movopfy.database.dao.favorites.FavouriteDao
import com.example.movopfy.database.models.favourite.FavouriteModel
import com.example.movopfy.features.details.domain.repository.FavouriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavouriteRepositoryImpl(private val favouriteDao: FavouriteDao) : FavouriteRepository {

    override suspend fun getFavouriteById(id: Int): FavouriteModel? =
        withContext(Dispatchers.IO) {
            favouriteDao.getFavouriteById(id = id)
        }

    override suspend fun addToFavourite(favouriteModel: FavouriteModel) =
        withContext(Dispatchers.IO) {
            favouriteDao.addToFavourite(favouriteModel)
        }

    override suspend fun removeFromFavourite(favouriteModel: FavouriteModel) =
        withContext(Dispatchers.IO) {
            favouriteDao.removeFromFavourite(favouriteModel = favouriteModel)
        }
}