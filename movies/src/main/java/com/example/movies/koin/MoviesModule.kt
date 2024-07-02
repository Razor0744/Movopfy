package com.example.movies.koin

import com.example.movies.data.repository.KinopoiskRepositoryImpl
import com.example.movies.domain.repository.KinopoiskRepository
import com.example.movies.presentation.viewmodel.MoviesViewModel
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