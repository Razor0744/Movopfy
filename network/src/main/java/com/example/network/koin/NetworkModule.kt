package com.example.network.koin

import com.example.network.anilibria.service.AnilibriaService
import com.example.network.kinopoisk.service.KinopoiskService
import com.example.network.retrofit.RetrofitClient
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

    fun provideKinopoiskService(retrofitClient: RetrofitClient, baseUrl: String): KinopoiskService =
        retrofitClient.getClient(baseUrl = baseUrl).create(KinopoiskService::class.java)

    single<KinopoiskService> {
        provideKinopoiskService(retrofitClient = get(), baseUrl = "https://api.kinopoisk.dev/v1.4/")
    }
}