package com.example.movopfy.features.favourite.koin

import com.example.movopfy.features.favourite.data.repository.FavouriteRepositoryImpl
import com.example.movopfy.features.favourite.domain.repository.FavouriteRepository
import com.example.movopfy.features.favourite.presentation.viewmodel.FavouriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoritesModule = module {

    viewModel<FavouriteViewModel> {
        FavouriteViewModel(favouriteRepository = get())
    }

    single<FavouriteRepository> {
        FavouriteRepositoryImpl(favouriteDao = get())
    }
}