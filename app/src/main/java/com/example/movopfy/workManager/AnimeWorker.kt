package com.example.movopfy.workManager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.common.extensions.getSmallImageUrl
import com.example.movopfy.database.dao.anime.AnimeSchedulesDao
import com.example.movopfy.database.models.anime.AnimeSchedules
import com.example.network.anilibria.service.AnilibriaService

class AnimeWorker(
    context: Context,
    workerParams: WorkerParameters,
    private val anilibriaService: AnilibriaService,
    private val animeSchedulesDao: AnimeSchedulesDao
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        try {
            val response = anilibriaService.getSchedule()

            val responseBody = if (response.isSuccessful) response.body() else null

            if (responseBody != null) {
                for (i in responseBody) {
                    if (i.list != null)
                        animeSchedulesDao.addAnimeSchedules(
                            animeSchedules = i.list!!
                                .map {
                                    AnimeSchedules(
                                        id = it.id ?: -1,
                                        pictureUrl = it.getSmallImageUrl() ?: "",
                                        day = i.day ?: -1
                                    )
                                }
                                .filter { it.day != -1 || it.id != -1 || it.pictureUrl.isNotEmpty() }
                                .toTypedArray()
                        )
                }
            } else {
                return Result.failure()
            }

            return Result.success()
        } catch (e: Exception) {
            return Result.failure()
        }
    }
}