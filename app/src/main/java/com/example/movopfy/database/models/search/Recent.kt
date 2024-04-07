package com.example.movopfy.database.models.search

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recent")
data class Recent(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long? = null,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "title_id") val titleId: Int,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "image_url") val url: String
)