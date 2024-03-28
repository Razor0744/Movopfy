package com.example.movopfy.database.models.details

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "details_state")
data class RoomDetailsState(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int? = null,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "picture_url") val pictureUrl: String,
    @ColumnInfo(name = "title_id") val titleId: Int,
    @ColumnInfo(name = "category") val category: String
)