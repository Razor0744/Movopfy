package com.example.movopfy.database.models.anime

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime_schedules")
data class AnimeSchedules(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "picture_url") val pictureUrl: String,
    @ColumnInfo(name = "day") val day: Int
)
