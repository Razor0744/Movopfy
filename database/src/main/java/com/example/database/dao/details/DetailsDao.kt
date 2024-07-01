package com.example.database.dao.details

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.database.models.details.Details
import com.example.database.models.details.DetailsWithEpisodes
import com.example.database.models.details.Episodes

@Dao
interface DetailsDao {

    @Transaction
    @Query("SELECT * FROM details_state WHERE title_id = :id AND category = :category")
    fun getTitleById(id: Int, category: String): DetailsWithEpisodes?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTitle(details: Details)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addEpisodes(vararg episodes: Episodes)
}