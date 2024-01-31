package com.example.movopfy.features.title.koin

import com.example.movopfy.features.title.data.repository.AnilibriaRepositoryImpl
import com.example.movopfy.features.title.domain.repository.AnilibriaRepository
import com.example.movopfy.features.title.presentation.viewmodel.TitleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val titleModule = module {

    single<AnilibriaRepository> {
        AnilibriaRepositoryImpl(anilibriaService = get())
    }

    viewModel<TitleViewModel> {
        TitleViewModel(anilibriaRepository = get())
    }
}