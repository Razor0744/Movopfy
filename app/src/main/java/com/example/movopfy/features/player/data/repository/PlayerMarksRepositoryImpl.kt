package com.example.movopfy.features.player.data.repository

import com.example.movopfy.database.dao.player.PlayerMarksDao
import com.example.movopfy.database.models.player.PlayerMarks
import com.example.movopfy.features.player.domain.repository.PlayerMarksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlayerMarksRepositoryImpl(private val playerMarksDao: PlayerMarksDao) :
    PlayerMarksRepository {

    override suspend fun getTimeById(id: Int, episode: Int): PlayerMarks? =
        withContext(Dispatchers.IO) {
            playerMarksDao.getTimeById(id = id, episode = episode)
        }

    override suspend fun setTime(playerMarks: PlayerMarks) = withContext(Dispatchers.IO) {
        if (playerMarks.id == null) {
            playerMarksDao.setTime(playerMarks = playerMarks)
        } else {
            playerMarksDao.updateTime(playerMarks = playerMarks)
        }
    }
}