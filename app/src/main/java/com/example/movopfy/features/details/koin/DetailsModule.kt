package com.example.movopfy.features.details.koin

import com.example.movopfy.features.details.data.repository.AnilibriaRepositoryImpl
import com.example.movopfy.features.details.data.repository.KinopoiskRepositoryImpl
import com.example.movopfy.features.details.domain.repository.AnilibriaRepository
import com.example.movopfy.features.details.domain.repository.KinopoiskRepository
import com.example.movopfy.features.details.presentation.viewmodel.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailsModule = module {

    single<AnilibriaRepository> {
        AnilibriaRepositoryImpl(anilibriaService = get())
    }

    single<KinopoiskRepository> {
        KinopoiskRepositoryImpl(kinopoiskService = get())
    }

    viewModel<DetailsViewModel> {
        DetailsViewModel(anilibriaRepository = get(), kinopoiskRepository = get())
    }
}