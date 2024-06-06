package com.example.movopfy.features.favourite.data.repository

import com.example.movopfy.database.dao.favorites.FavouriteDao
import com.example.movopfy.database.models.favourite.FavouriteModel
import com.example.movopfy.features.favourite.domain.repository.FavouriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavouriteRepositoryImpl(private val favouriteDao: FavouriteDao) : FavouriteRepository {

    override suspend fun getFavourites(): List<FavouriteModel> =
        withContext(Dispatchers.IO) {
            favouriteDao.getFavourites()
        }

    override suspend fun removeFromFavourite(favouriteModel: FavouriteModel) =
        withContext(Dispatchers.IO) {
            favouriteDao.removeFromFavourite(favouriteModel = favouriteModel)
        }
}