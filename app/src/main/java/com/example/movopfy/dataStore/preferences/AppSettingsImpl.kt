package com.example.movopfy.dataStore.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class AppSettingsImpl(private val context: Context) : AppSettings {

    override suspend fun getInt(key: String): Int {
        return context.dataStore.data.map {
            it[intPreferencesKey(name = key)] ?: 0
        }.first()
    }

    override suspend fun setInt(key: String, value: Int) {
        context.dataStore.edit {
            it[intPreferencesKey(name = key)] = value
        }
    }
}