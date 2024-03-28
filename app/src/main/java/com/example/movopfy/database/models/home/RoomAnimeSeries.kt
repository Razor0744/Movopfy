package com.example.movopfy.database.models.home

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime_series")
data class RoomAnimeSeries(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "picture_url") val pictureUrl: String,
    @ColumnInfo(name = "day") val day: Int
)