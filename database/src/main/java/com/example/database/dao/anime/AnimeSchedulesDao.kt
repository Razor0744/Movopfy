package com.example.database.dao.anime

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.models.anime.AnimeSchedules

@Dao
interface AnimeSchedulesDao {

    @Query("SELECT * FROM anime_schedules")
    fun getAnimeSchedules(): List<AnimeSchedules>

    @Query("SELECT * FROM anime_schedules WHERE day = :currentDay")
    fun getAnimeListByDay(currentDay: Int): List<AnimeSchedules>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAnimeSchedules(vararg animeSchedules: AnimeSchedules)
}