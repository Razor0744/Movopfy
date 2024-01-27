package com.example.movopfy.features.home.koin

import com.example.movopfy.features.home.data.api.anilibria.AnilibriaService
import com.example.movopfy.features.home.data.repository.AnilibriaRepositoryImpl
import com.example.movopfy.features.home.domain.repository.AnilibriaRepository
import com.example.movopfy.features.home.domain.usecase.GetWaitingListTodayUseCase
import com.example.movopfy.features.home.presentation.HomeViewModel
import com.example.movopfy.network.RetrofitClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {

    fun provideAnilibriaService(retrofitClient: RetrofitClient, baseUrl: String): AnilibriaService =
        retrofitClient.getClient(baseUrl = baseUrl).create(AnilibriaService::class.java)

    viewModel<HomeViewModel> {
        HomeViewModel(getWaitingListTodayUseCase = get())
    }

    single<AnilibriaService> {
        provideAnilibriaService(retrofitClient = get(), baseUrl = "https://api.anilibria.tv/v3/")
    }

    single<AnilibriaRepository> {
        AnilibriaRepositoryImpl(anilibriaService = get())
    }

    factory<GetWaitingListTodayUseCase> {
        GetWaitingListTodayUseCase(anilibriaRepository = get())
    }
}