package com.example.datastore.koin

import com.example.datastore.preferences.AppSettings
import com.example.datastore.preferences.AppSettingsImpl
import org.koin.dsl.module

val dataStoreModule = module {

    single<AppSettings> {
        AppSettingsImpl(context = get())
    }
}