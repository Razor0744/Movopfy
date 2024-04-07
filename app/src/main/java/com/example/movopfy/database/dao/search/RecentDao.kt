package com.example.movopfy.database.dao.search

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movopfy.database.models.search.Recent

@Dao
interface RecentDao {

    @Query("SELECT * FROM recent")
    fun getRecent(): List<Recent>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addToRecent(recent: Recent)

    @Delete
    fun removeFromRecent(recent: Recent)
}