package com.example.movopfy.features.title.koin

import com.example.movopfy.features.title.data.api.anilibria.AnilibriaService
import com.example.movopfy.features.title.data.repository.AnilibriaRepositoryImpl
import com.example.movopfy.features.title.domain.repository.AnilibriaRepository
import com.example.movopfy.features.title.presentation.TitleViewModel
import com.example.movopfy.network.RetrofitClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val titleModule = module {

    fun provideAnilibriaService(retrofitClient: RetrofitClient, baseUrl: String): AnilibriaService =
        retrofitClient.getClient(baseUrl = baseUrl).create(AnilibriaService::class.java)

    single<AnilibriaService> {
        provideAnilibriaService(retrofitClient = get(), baseUrl = "https://api.anilibria.tv/v3/")
    }

    single<AnilibriaRepository> {
        AnilibriaRepositoryImpl(anilibriaService = get())
    }

    viewModel<TitleViewModel> {
        TitleViewModel(anilibriaRepository = get())
    }
}