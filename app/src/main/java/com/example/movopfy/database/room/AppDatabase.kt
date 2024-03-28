package com.example.movopfy.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movopfy.database.dao.anime.AnimeSchedulesDao
import com.example.movopfy.database.dao.details.DetailsStateDao
import com.example.movopfy.database.dao.favorites.FavouriteDao
import com.example.movopfy.database.dao.home.AnimeSeriesDao
import com.example.movopfy.database.dao.home.KinopoiskDocsDao
import com.example.movopfy.database.models.anime.RoomAnimeSchedules
import com.example.movopfy.database.models.details.RoomDetailsState
import com.example.movopfy.database.models.details.RoomEpisodes
import com.example.movopfy.database.models.favourite.RoomFavourite
import com.example.movopfy.database.models.home.RoomAnimeSeries
import com.example.movopfy.database.models.home.RoomKinopoiskDocs

@Database(
    version = 6,
    entities = [
        RoomAnimeSeries::class,
        RoomKinopoiskDocs::class,
        RoomAnimeSchedules::class,
        RoomDetailsState::class,
        RoomEpisodes::class,
        RoomFavourite::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun animeSeriesDao(): AnimeSeriesDao

    abstract fun kinopoiskDocsDao(): KinopoiskDocsDao

    abstract fun animeSchedulesDao(): AnimeSchedulesDao

    abstract fun detailsStateDao(): DetailsStateDao

    abstract fun favouriteDao(): FavouriteDao
}