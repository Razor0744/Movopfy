package com.example.details.koin

import com.example.details.data.repository.AnilibriaRepositoryImpl
import com.example.details.data.repository.FavouriteRepositoryImpl
import com.example.details.data.repository.KinopoiskRepositoryImpl
import com.example.details.domain.repository.AnilibriaRepository
import com.example.details.domain.repository.FavouriteRepository
import com.example.details.domain.repository.KinopoiskRepository
import com.example.details.presentation.viewmodel.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailsModule = module {

    single<AnilibriaRepository> {
        AnilibriaRepositoryImpl(anilibriaService = get(), detailsDao = get())
    }

    single<KinopoiskRepository> {
        KinopoiskRepositoryImpl(kinopoiskService = get(), detailsDao = get())
    }

    viewModel<DetailsViewModel> {
        DetailsViewModel(
            anilibriaRepository = get(),
            kinopoiskRepository = get(),
            favouriteRepository = get()
        )
    }

    single<FavouriteRepository> {
        FavouriteRepositoryImpl(favouriteDao = get())
    }
}