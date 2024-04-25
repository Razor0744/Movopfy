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
        val intKey = intPreferencesKey(name = key)

        context.dataStore.data.map {
            it[intKey]
        }
    }

    override suspend fun setInt(key: String, value: Int): Unit = withContext(Dispatchers.Default) {
        val intKey = intPreferencesKey(name = key)

        context.dataStore.edit {
            it[intKey] = value
        }
    }
}