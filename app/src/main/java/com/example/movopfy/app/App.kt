package com.example.movopfy.app

import android.app.Application
import com.example.movopfy.dataStore.koin.dataStoreModule
import com.example.movopfy.database.koin.databaseModule
import com.example.movopfy.features.anime.koin.animeModule
import com.example.movopfy.features.details.koin.detailsModule
import com.example.movopfy.features.favourite.koin.favoritesModule
import com.example.movopfy.features.home.koin.homeModule
import com.example.movopfy.features.movies.koin.moviesModule
import com.example.movopfy.features.player.koin.playerModule
import com.example.movopfy.features.search.koin.searchModule
import com.example.movopfy.network.koin.networkModule
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
            modules(
                listOf(
                    homeModule,
                    networkModule,
                    detailsModule,
                    moviesModule,
                    animeModule,
                    playerModule,
                    favoritesModule,
                    databaseModule,
                    searchModule,
                    dataStoreModule
                )
            )
        }
    }
}