package com.example.database.koin

import androidx.room.Room
import com.example.database.dao.anime.AnimeSchedulesDao
import com.example.database.dao.details.DetailsDao
import com.example.database.dao.favorites.FavouriteDao
import com.example.database.dao.home.KinopoiskDocsDao
import com.example.database.dao.player.PlayerMarksDao
import com.example.database.dao.search.RecentDao
import com.example.database.room.AppDatabase
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