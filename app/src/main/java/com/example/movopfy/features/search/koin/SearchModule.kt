package com.example.movopfy.features.search.koin

import com.example.movopfy.features.search.data.repository.AnilibriaRepositoryImpl
import com.example.movopfy.features.search.data.repository.KinopoiskRepositoryImpl
import com.example.movopfy.features.search.data.repository.RoomRepositoryImpl
import com.example.movopfy.features.search.domain.repository.AnilibriaRepository
import com.example.movopfy.features.search.domain.repository.KinopoiskRepository
import com.example.movopfy.features.search.domain.repository.RoomRepository
import com.example.movopfy.features.search.presentation.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {

    viewModel<SearchViewModel> {
        SearchViewModel(
            anilibriaRepository = get(),
            kinopoiskRepository = get(),
            roomRepository = get()
        )
    }

    single<AnilibriaRepository> {
        AnilibriaRepositoryImpl(anilibriaService = get())
    }

    single<KinopoiskRepository> {
        KinopoiskRepositoryImpl(kinopoiskService = get())
    }

    single<RoomRepository> {
        RoomRepositoryImpl(recentDao = get())
    }
}