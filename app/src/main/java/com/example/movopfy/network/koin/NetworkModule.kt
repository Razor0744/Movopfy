package com.example.movopfy.network.koin

import com.example.movopfy.network.retrofit.RetrofitClient
import com.example.movopfy.network.anilibria.service.AnilibriaService
import org.koin.dsl.module

val networkModule = module {

    single<RetrofitClient> {
        RetrofitClient()
    }

    fun provideAnilibriaService(retrofitClient: RetrofitClient, baseUrl: String): AnilibriaService =
        retrofitClient.getClient(baseUrl = baseUrl).create(AnilibriaService::class.java)

    single<AnilibriaService> {
        provideAnilibriaService(retrofitClient = get(), baseUrl = "https://api.anilibria.tv/v3/")
    }
}