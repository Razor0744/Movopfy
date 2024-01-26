package com.example.movopfy.di

import com.example.movopfy.features.home.data.api.anilibria.Anilibria
import com.example.movopfy.features.home.data.api.anilibria.AnilibriaURL
import com.example.movopfy.features.home.data.repository.AnilibriaRepositoryImpl
import com.example.movopfy.features.home.domain.repository.AnilibriaRepository
import com.example.movopfy.retrofit.RetrofitClient
import org.koin.dsl.module

val dataModule = module {

    single<RetrofitClient> {
        RetrofitClient
    }

    single<Anilibria> {
        Anilibria(anilibriaURL = get())
    }

    single<AnilibriaURL> {
        AnilibriaURL(retrofitClient = get())
    }

    single<AnilibriaRepository> {
        AnilibriaRepositoryImpl(anilibria = get())
    }
}