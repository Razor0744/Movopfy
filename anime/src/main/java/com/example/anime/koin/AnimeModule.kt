package com.example.anime.koin

import com.example.anime.data.repository.AnilibriaRepositoryImpl
import com.example.anime.domain.repository.AnilibriaRepository
import com.example.anime.presentation.viewmodel.AnimeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val animeModule = module {

    viewModel<AnimeViewModel> {
        AnimeViewModel(anilibriaRepository = get())
    }

    single<AnilibriaRepository> {
        AnilibriaRepositoryImpl(
            anilibriaService = get(),
            animeSchedulesDao = get(),
            appSettings = get()
        )
    }
}