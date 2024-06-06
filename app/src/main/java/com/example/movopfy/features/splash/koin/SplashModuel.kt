package com.example.movopfy.features.splash.koin

import com.example.movopfy.features.splash.presentation.viewModel.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashModule = module {

    viewModel<SplashViewModel> {
        SplashViewModel(firestoreFavourites = get(), favouriteDao = get(), appSettings = get())
    }
}