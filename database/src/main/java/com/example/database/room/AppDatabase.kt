package com.example.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.dao.anime.AnimeSchedulesDao
import com.example.database.dao.details.DetailsDao
import com.example.database.dao.favorites.FavouriteDao
import com.example.database.dao.home.KinopoiskDocsDao
import com.example.database.dao.player.PlayerMarksDao
import com.example.database.dao.search.RecentDao
import com.example.database.models.anime.AnimeSchedules
import com.example.database.models.details.Details
import com.example.database.models.details.Episodes
import com.example.database.models.favourite.FavouriteModel
import com.example.database.models.home.Kinopoisk
import com.example.database.models.player.PlayerMarks
import com.example.database.models.search.Recent

@Database(
    version = 11,
    entities = [
        Kinopoisk::class,
        AnimeSchedules::class,
        Details::class,
        Episodes::class,
        FavouriteModel::class,
        Recent::class,
        PlayerMarks::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun kinopoiskDocsDao(): KinopoiskDocsDao

    abstract fun animeSchedulesDao(): AnimeSchedulesDao

    abstract fun detailsStateDao(): DetailsDao

    abstract fun favouriteDao(): FavouriteDao

    abstract fun recentDao(): RecentDao

    abstract fun playerMarksDao(): PlayerMarksDao
}