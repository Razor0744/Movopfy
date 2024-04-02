package com.example.movopfy.database.dao.anime

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.movopfy.database.models.anime.AnimeSchedules

@Dao
interface AnimeSchedulesDao {

    @Query("SELECT * FROM anime_schedules")
    fun getAnimeSchedules(): List<AnimeSchedules>

    @Insert
    fun addAnimeSchedules(vararg animeSchedules: AnimeSchedules)
}