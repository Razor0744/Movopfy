package com.example.movopfy.app

import android.app.Application
import com.example.database.koin.databaseModule
import com.example.datastore.koin.dataStoreModule
import com.example.firebase.koin.firebaseModule
import com.example.home.koin.homeModule
import com.example.anime.koin.animeModule
import com.example.auth.koin.authModule
import com.example.details.koin.detailsModule
import com.example.favourite.koin.favoritesModule
import com.example.movies.koin.moviesModule
import com.example.player.koin.playerModule
import com.example.search.koin.searchModule
import com.example.network.koin.networkModule
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
                    dataStoreModule,
                    authModule,
                    firebaseModule,
                    appModule
                )
            )
        }
    }
}