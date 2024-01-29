package com.example.movopfy.app

import android.app.Application
import com.example.movopfy.features.home.koin.homeModule
import com.example.movopfy.features.title.koin.titleModule
import com.example.movopfy.network.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(homeModule, networkModule, titleModule))
        }
    }
}