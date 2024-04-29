package com.example.movopfy.dataStore.preferences

import androidx.datastore.preferences.core.Preferences

interface AppSettings {

    suspend fun getInt(key: Preferences.Key<Int>): Int

    suspend fun setInt(key: Preferences.Key<Int>, value: Int)
}