package com.example.home.koin

import com.example.home.data.repository.AnilibriaRepositoryImpl
import com.example.home.data.repository.KinopoiskRepositoryImpl
import com.example.home.domain.repository.AnilibriaRepository
import com.example.home.domain.repository.KinopoiskRepository
import com.example.home.domain.usecase.GetHomeDataUseCase
import com.example.home.presentation.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {

    viewModel<HomeViewModel> {
        HomeViewModel(getHomeDataUseCase = get())
    }

    single<AnilibriaRepository> {
        AnilibriaRepositoryImpl(
            anilibriaService = get(),
            appSettings = get(),
            animeSchedulesDao = get()
        )
    }

    single<KinopoiskRepository> {
        KinopoiskRepositoryImpl(kinopoiskService = get(), kinopoiskDocsDao = get())
    }

    factory<GetHomeDataUseCase> {
        GetHomeDataUseCase(
            anilibriaRepository = get(),
            kinopoiskRepository = get()
        )
    }
}