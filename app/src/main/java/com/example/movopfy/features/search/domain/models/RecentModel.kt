package com.example.movopfy.features.search.domain.models

data class RecentModel(
    val id: Long? = 0,
    val name: String,
    val titleId: Int,
    val category: String,
    val url: String
)