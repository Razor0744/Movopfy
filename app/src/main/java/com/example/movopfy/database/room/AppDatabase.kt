package com.example.movopfy.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movopfy.database.dao.anime.AnimeSchedulesDao
import com.example.movopfy.database.dao.details.DetailsDao
import com.example.movopfy.database.dao.favorites.FavouriteDao
import com.example.movopfy.database.dao.home.AnimeSeriesDao
import com.example.movopfy.database.dao.home.KinopoiskDocsDao
import com.example.movopfy.database.dao.search.RecentDao
import com.example.movopfy.database.models.anime.AnimeSchedules
import com.example.movopfy.database.models.details.Details
import com.example.movopfy.database.models.details.Episodes
import com.example.movopfy.database.models.favourite.FavouriteModel
import com.example.movopfy.database.models.home.Anime
import com.example.movopfy.database.models.home.Kinopoisk
import com.example.movopfy.database.models.search.Recent

@Database(
    version = 7,
    entities = [
        Anime::class,
        Kinopoisk::class,
        AnimeSchedules::class,
        Details::class,
        Episodes::class,
        FavouriteModel::class,
        Recent::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun animeSeriesDao(): AnimeSeriesDao

    abstract fun kinopoiskDocsDao(): KinopoiskDocsDao

    abstract fun animeSchedulesDao(): AnimeSchedulesDao

    abstract fun detailsStateDao(): DetailsDao

    abstract fun favouriteDao(): FavouriteDao

    abstract fun recentDao(): RecentDao
}