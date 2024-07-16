package com.example.auth.koin

import com.example.auth.data.repository.FirebaseRepositoryImpl
import com.example.auth.domain.repository.FirebaseRepository
import com.example.auth.presentation.viewModel.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {

    viewModel<AuthViewModel> {
        AuthViewModel(firebaseRepository = get())
    }

    single<FirebaseRepository> {
        FirebaseRepositoryImpl(userManager = get())
    }
}