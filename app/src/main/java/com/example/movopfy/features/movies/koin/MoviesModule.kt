package com.example.movopfy.features.movies.koin

import com.example.movopfy.features.movies.data.repository.KinopoiskRepositoryImpl
import com.example.movopfy.features.movies.domain.repository.KinopoiskRepository
import com.example.movopfy.features.movies.presentation.viewmodel.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moviesModule = module {
    viewModel<MoviesViewModel> {
        MoviesViewModel(kinopoiskRepository = get())
    }

    single<KinopoiskRepository> {
        KinopoiskRepositoryImpl(kinopoiskService = get())
    }
}