package com.example.movopfy.di

import com.example.movopfy.features.home.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<HomeViewModel> {
        HomeViewModel(getWaitingListTodayUseCase = get())
    }
}