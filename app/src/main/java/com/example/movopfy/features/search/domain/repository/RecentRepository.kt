package com.example.movopfy.features.search.domain.repository

import com.example.movopfy.features.search.domain.models.RecentModel

interface RecentRepository {

    suspend fun getRecent(): List<RecentModel>

    suspend fun addToRecent(recentModel: RecentModel)

    suspend fun removeFromRecent(recentModel: RecentModel)
}