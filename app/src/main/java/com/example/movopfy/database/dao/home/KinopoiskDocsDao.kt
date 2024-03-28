package com.example.movopfy.database.dao.home

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movopfy.database.models.home.RoomKinopoiskDocs

@Dao
interface KinopoiskDocsDao {

    @Query("SELECT * FROM kinopoisk_docs WHERE category = :category")
    fun getKinopoiskDocsByCategory(category: String): List<RoomKinopoiskDocs>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addKinopoiskDocs(vararg roomKinopoiskDocs: RoomKinopoiskDocs)
}