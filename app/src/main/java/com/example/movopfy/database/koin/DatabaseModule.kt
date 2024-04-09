package com.example.movopfy.database.koin

import androidx.room.Room
import com.example.movopfy.database.dao.anime.AnimeSchedulesDao
import com.example.movopfy.database.dao.details.DetailsDao
import com.example.movopfy.database.dao.favorites.FavouriteDao
import com.example.movopfy.database.dao.home.AnimeSeriesDao
import com.example.movopfy.database.dao.home.KinopoiskDocsDao
import com.example.movopfy.database.dao.player.PlayerMarksDao
import com.example.movopfy.database.dao.search.RecentDao
import com.example.movopfy.database.room.AppDatabase
import org.koin.dsl.module

val databaseModule = module {

    single<AppDatabase> {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single<AnimeSeriesDao> {
        get<AppDatabase>().animeSeriesDao()
    }

    single<KinopoiskDocsDao> {
        get<AppDatabase>().kinopoiskDocsDao()
    }

    single<AnimeSchedulesDao> {
        get<AppDatabase>().animeSchedulesDao()
    }

    single<DetailsDao> {
        get<AppDatabase>().detailsStateDao()
    }

    single<FavouriteDao> {
        get<AppDatabase>().favouriteDao()
    }

    single<RecentDao> {
        get<AppDatabase>().recentDao()
    }

    single<PlayerMarksDao> {
        get<AppDatabase>().playerMarksDao()
    }
}