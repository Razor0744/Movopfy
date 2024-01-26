package com.example.movopfy.di

import com.example.movopfy.features.home.domain.usecase.GetWaitingListTodayUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetWaitingListTodayUseCase> {
        GetWaitingListTodayUseCase(anilibriaRepository = get())
    }

}