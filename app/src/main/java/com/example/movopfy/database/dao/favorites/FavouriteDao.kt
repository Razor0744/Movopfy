package com.example.movopfy.database.dao.favorites

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movopfy.database.models.favourite.Favourite

@Dao
interface FavouriteDao {

    @Query("SELECT * FROM favourite")
    fun getFavourite(): List<Favourite>

    @Query("SELECT * FROM favourite WHERE title_id = :id")
    fun getFavouriteById(id: Int): Favourite?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addToFavourite(favourite: Favourite)

    @Delete
    fun removeFromFavourite(favourite: Favourite)
}