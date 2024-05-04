package com.example.movopfy.datastore.koin

import com.example.movopfy.datastore.preferences.AppSettings
import com.example.movopfy.datastore.preferences.AppSettingsImpl
import org.koin.dsl.module

val dataStoreModule = module {

    single<AppSettings> {
        AppSettingsImpl(context = get())
    }
}