package com.example.movopfy.database.dao.details

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.movopfy.database.models.details.Details
import com.example.movopfy.database.models.details.DetailsWithEpisodes
import com.example.movopfy.database.models.details.Episodes

@Dao
interface DetailsStateDao {

    @Transaction
    @Query("SELECT * FROM details_state WHERE id = :id AND category = :category")
    fun getTitleById(id: Int, category: String): DetailsWithEpisodes?

    @Insert
    fun addTitle(details: Details)

    @Insert
    fun addEpisodes(vararg episodes: Episodes)
}