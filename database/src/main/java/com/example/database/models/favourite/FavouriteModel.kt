package com.example.database.models.favourite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite")
data class FavouriteModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long? = null,
    @ColumnInfo(name = "title_id") val titleId: Int,
    @ColumnInfo(name = "picture_url") val url: String,
    @ColumnInfo(name = "category") val category: String
)