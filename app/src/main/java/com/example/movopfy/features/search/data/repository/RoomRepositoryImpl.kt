package com.example.movopfy.features.search.data.repository

import com.example.movopfy.database.dao.search.RecentDao
import com.example.movopfy.database.models.search.Recent
import com.example.movopfy.features.search.domain.models.RecentModel
import com.example.movopfy.features.search.domain.repository.RoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomRepositoryImpl(private val recentDao: RecentDao) : RoomRepository {

    override suspend fun getRecent(): List<RecentModel> = withContext(Dispatchers.IO) {
        recentDao.getRecent().map {
            RecentModel(
                id = it.id ?: 0,
                name = it.name,
                titleId = it.titleId,
                category = it.category,
                url = it.url
            )
        }
    }

    override suspend fun addToRecent(recentModel: RecentModel) = withContext(Dispatchers.IO) {
        recentDao.addToRecent(
            recent = Recent(
                id = null,
                name = recentModel.name,
                titleId = recentModel.titleId,
                category = recentModel.category,
                url = recentModel.url
            )
        )
    }

    override suspend fun removeFromRecent(recentModel: RecentModel) = withContext(Dispatchers.IO) {
        recentDao.removeFromRecent(
            recent = Recent(
                id = recentModel.id,
                name = recentModel.name,
                titleId = recentModel.titleId,
                category = recentModel.category,
                url = recentModel.url
            )
        )
    }
}