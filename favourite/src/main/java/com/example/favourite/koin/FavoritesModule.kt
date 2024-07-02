package com.example.favourite.koin

import com.example.favourite.data.repository.FavouriteRepositoryImpl
import com.example.favourite.domain.repository.FavouriteRepository
import com.example.favourite.presentation.viewmodel.FavouriteViewModel
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