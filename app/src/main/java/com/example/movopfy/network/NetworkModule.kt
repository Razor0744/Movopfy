package com.example.movopfy.network

import org.koin.dsl.module

val networkModule = module {

    single<RetrofitClient> {
        RetrofitClient()
    }
}