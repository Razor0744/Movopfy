package com.example.movopfy.dataStore.koin

import com.example.movopfy.dataStore.preferences.AppSettings
import com.example.movopfy.dataStore.preferences.AppSettingsImpl
import org.koin.dsl.module

val dataStoreModule = module {

    single<AppSettings> {
        AppSettingsImpl(context = get())
    }
}