package com.example.movopfy.database.models.player

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player_marks")
data class PlayerMarks(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int? = null,
    @ColumnInfo(name = "current_time") val currentTime: Long,
    @ColumnInfo(name = "episode_id") val episodeId: Int,
    @ColumnInfo(name = "title_id") val titleId: Int
)
