package com.example.movopfy.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.constants.PreferencesKeys
import com.example.common.extensions.dateWithTime
import com.example.movopfy.database.dao.favorites.FavouriteDao
import com.example.movopfy.database.models.favourite.FavouriteModel
import com.example.movopfy.datastore.preferences.AppSettings
import com.example.movopfy.firebase.model.FirestoreDataNewerResult
import com.example.movopfy.firebase.model.FirestoreFavouriteModel
import com.example.movopfy.firebase.synchronization.FirestoreFavourites
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar

class MainViewModel(
    private val firestoreFavourites: FirestoreFavourites,
    private val favouriteDao: FavouriteDao,
    private val appSettings: AppSettings,
) : ViewModel() {

    private val _uiState = MutableStateFlow(true)
    val uiState: StateFlow<Boolean> = _uiState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val firestoreDataNewer =
                firestoreFavourites.firestoreDataNewer(date = appSettings.getInt(key = com.example.common.constants.PreferencesKeys.SYNCHRONIZATION_DATE))

            when (firestoreDataNewer) {
                is FirestoreDataNewerResult.Success -> {
                    if (firestoreDataNewer.isNewer) {
                        val anilibriaFavourites = firestoreFavourites.getFavouritesAnilibria()
                        val kinopoiskFavourites = firestoreFavourites.getFavouritesKinopoisk()
                        val favourites = (anilibriaFavourites + kinopoiskFavourites).map {
                            FavouriteModel(
                                titleId = it.titleId,
                                url = it.url,
                                category = it.category
                            )
                        }.toTypedArray()

                        if (favourites.isNotEmpty()) {
                            favouriteDao.deleteAllFavourites()

                            favouriteDao.addToFavourite(favouriteModel = favourites)
                        }
                    } else {
                        val roomFavourites = favouriteDao.getFavourites()

                        val fireStoreFavouritesData =
                            roomFavourites.map {
                                FirestoreFavouriteModel(
                                    titleId = it.titleId,
                                    url = it.url,
                                    category = it.category
                                )
                            }

                        firestoreFavourites.deleteAllDocumentsAnilibria()
                        firestoreFavourites.deleteAllDocumentsKinopoisk()
                        firestoreFavourites.setFavourites(favourites = fireStoreFavouritesData)
                    }

                    firestoreFavourites.setDateSync()
                    appSettings.setInt(
                        key = com.example.common.constants.PreferencesKeys.SYNCHRONIZATION_DATE,
                        value = Calendar.getInstance().dateWithTime()
                    )

                    _uiState.value = false
                }

                is FirestoreDataNewerResult.Fail -> {
                    _uiState.value = false
                }
            }
        }
    }
}