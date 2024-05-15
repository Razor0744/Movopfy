package com.example.movopfy.database.dao.player

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.movopfy.database.models.player.PlayerMarks

@Dao
interface PlayerMarksDao {

    @Query("SELECT * FROM player_marks WHERE title_id = :id AND episode_id = :episode")
    fun getTimeById(id: Int, episode: Int): PlayerMarks?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setTime(playerMarks: PlayerMarks)

    @Update
    fun updateTime(playerMarks: PlayerMarks)
}