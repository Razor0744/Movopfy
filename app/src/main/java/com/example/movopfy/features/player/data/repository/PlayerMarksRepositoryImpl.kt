package com.example.movopfy.features.player.data.repository

import com.example.movopfy.database.dao.player.PlayerMarksDao
import com.example.movopfy.database.models.player.PlayerMarks
import com.example.movopfy.features.player.domain.PlayerMark
import com.example.movopfy.features.player.domain.repository.PlayerMarksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlayerMarksRepositoryImpl(private val playerMarksDao: PlayerMarksDao) :
    PlayerMarksRepository {

    override suspend fun getTimeById(id: Int, episode: Int): Long = withContext(Dispatchers.IO) {
        playerMarksDao.getTimeById(id = id, episode = episode)?.currentTime ?: 0
    }

    override suspend fun setTime(playerMark: PlayerMark) = withContext(Dispatchers.IO) {
        playerMarksDao.setTime(
            playerMarks = PlayerMarks(
                id = playerMark.id,
                currentTime = playerMark.currentTime,
                episodeId = playerMark.episodeId
            )
        )
    }
}