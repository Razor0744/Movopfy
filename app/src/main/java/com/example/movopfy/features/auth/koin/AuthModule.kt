package com.example.movopfy.features.auth.koin

import com.example.movopfy.features.auth.presentation.viewModel.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {

    viewModel<AuthViewModel> {
        AuthViewModel()
    }
}