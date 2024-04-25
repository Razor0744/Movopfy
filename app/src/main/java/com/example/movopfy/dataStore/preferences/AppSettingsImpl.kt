package com.example.movopfy.dataStore.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class AppSettingsImpl(private val context: Context): AppSettings {

    override suspend fun getInt(key: String): Flow<Int?> = withContext(Dispatchers.Default) {
        context.dataStore.data.map {
            it[intPreferencesKey(name = key)]
        }
    }

    override suspend fun setInt(key: String, value: Int): Unit = withContext(Dispatchers.Default) {
        context.dataStore.edit {
            it[intPreferencesKey(name = key)] = value
        }
    }
}