package com.example.search.koin

import com.example.search.data.repository.AnilibriaRepositoryImpl
import com.example.search.data.repository.KinopoiskRepositoryImpl
import com.example.search.data.repository.RecentRepositoryImpl
import com.example.search.domain.repository.AnilibriaRepository
import com.example.search.domain.repository.KinopoiskRepository
import com.example.search.domain.repository.RecentRepository
import com.example.search.presentation.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {

    viewModel<SearchViewModel> {
        SearchViewModel(
            anilibriaRepository = get(),
            kinopoiskRepository = get(),
            recentRepository = get()
        )
    }

    single<AnilibriaRepository> {
        AnilibriaRepositoryImpl(anilibriaService = get())
    }

    single<KinopoiskRepository> {
        KinopoiskRepositoryImpl(kinopoiskService = get())
    }

    single<RecentRepository> {
        RecentRepositoryImpl(recentDao = get())
    }
}