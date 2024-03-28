package com.example.movopfy.features.anime.koin

import com.example.movopfy.features.anime.data.repository.AnilibriaRepositoryImpl
import com.example.movopfy.features.anime.domain.repository.AnilibriaRepository
import com.example.movopfy.features.anime.presentation.viewmodel.AnimeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val animeModule = module {
    viewModel<AnimeViewModel> {
        AnimeViewModel(anilibriaRepository = get())
    }

    single<AnilibriaRepository> {
        AnilibriaRepositoryImpl(
            anilibriaService = get(),
            animeSchedulesDao = get()
        )
    }
}