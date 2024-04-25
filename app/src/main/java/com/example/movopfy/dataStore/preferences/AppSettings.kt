package com.example.movopfy.dataStore.preferences

interface AppSettings {

    suspend fun getInt(key: String): Int

    suspend fun setInt(key: String, value: Int)
}