package com.example.movopfy.dataStore.preferences

import kotlinx.coroutines.flow.Flow

interface AppSettings {

    suspend fun getInt(key: String): Flow<Int?>

    suspend fun setInt(key: String, value: Int)
}