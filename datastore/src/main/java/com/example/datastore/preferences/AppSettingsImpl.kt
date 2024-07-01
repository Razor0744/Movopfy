package com.example.datastore.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class AppSettingsImpl(private val context: Context) : AppSettings {

    override suspend fun getInt(key: Preferences.Key<Int>): Int {
        return context.dataStore.data.map {
            it[key] ?: 0
        }.first()
    }

    override suspend fun setInt(key: Preferences.Key<Int>, value: Int) {
        context.dataStore.edit {
            it[key] = value
        }
    }
}