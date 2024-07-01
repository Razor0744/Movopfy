package com.example.database.dao.home

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.models.home.Kinopoisk

@Dao
interface KinopoiskDocsDao {

    @Query("SELECT * FROM kinopoisk_docs WHERE category = :category")
    fun getKinopoiskDocsByCategory(category: String): List<Kinopoisk>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addKinopoiskDocs(vararg kinopoisk: Kinopoisk)
}