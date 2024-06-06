package com.example.movopfy.app

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<MainViewModel> {
        MainViewModel(
            firestoreFavourites = get(),
            favouriteDao = get(),
            appSettings = get()
        )
    }
}