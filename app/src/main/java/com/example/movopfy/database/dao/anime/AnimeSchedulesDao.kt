package com.example.movopfy.database.dao.anime

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.movopfy.database.models.anime.RoomAnimeSchedules

@Dao
interface AnimeSchedulesDao {

    @Query("SELECT * FROM anime_schedules")
    fun getAnimeSchedules(): List<RoomAnimeSchedules>

    @Insert
    fun addAnimeSchedules(vararg roomAnimeSchedules: RoomAnimeSchedules)
}