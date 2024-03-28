package com.example.movopfy.database.dao.details

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.movopfy.database.models.details.RoomDetailsState
import com.example.movopfy.database.models.details.RoomDetailsStateWithEpisodes
import com.example.movopfy.database.models.details.RoomEpisodes

@Dao
interface DetailsStateDao {

    @Transaction
    @Query("SELECT * FROM details_state WHERE id = :id AND category = :category")
    fun getTitleById(id: Int, category: String): RoomDetailsStateWithEpisodes?

    @Insert
    fun addTitle(roomDetailsState: RoomDetailsState)

    @Insert
    fun addEpisodes(vararg roomEpisodes: RoomEpisodes)
}