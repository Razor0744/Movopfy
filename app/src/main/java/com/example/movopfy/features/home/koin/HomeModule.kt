package com.example.movopfy.features.home.koin

import com.example.movopfy.features.home.data.repository.AnilibriaRepositoryImpl
import com.example.movopfy.features.home.domain.repository.AnilibriaRepository
import com.example.movopfy.features.home.domain.usecase.GetWaitingListTodayUseCase
import com.example.movopfy.features.home.presentation.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {

    viewModel<HomeViewModel> {
        HomeViewModel(getWaitingListTodayUseCase = get())
    }

    single<AnilibriaRepository> {
        AnilibriaRepositoryImpl(anilibriaService = get())
    }

    factory<GetWaitingListTodayUseCase> {
        GetWaitingListTodayUseCase(anilibriaRepository = get())
    }
}