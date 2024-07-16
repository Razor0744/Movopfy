package com.example.favourite.data.repository

import com.example.database.dao.favorites.FavouriteDao
import com.example.database.models.favourite.FavouriteModel
import com.example.favourite.domain.repository.FavouriteRepository
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