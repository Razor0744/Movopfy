package com.example.movopfy.database.dao.home

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.movopfy.database.models.home.Anime

@Dao
interface AnimeSeriesDao {

    @Query("SELECT * FROM anime_series WHERE day = :currentDay")
    fun getAnimeSeriesList(currentDay: Int): List<Anime>

    @Insert
    fun addAnimeSeries(vararg animeSeries: Anime)

    @Update
    fun updateAnimeSeries(vararg animeSeries: Anime)
}