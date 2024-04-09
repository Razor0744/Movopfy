package com.example.movopfy.features.player.koin

import com.example.movopfy.features.player.data.repository.AnilibriaRepositoryImpl
import com.example.movopfy.features.player.data.repository.PlayerMarksRepositoryImpl
import com.example.movopfy.features.player.domain.repository.AnilibriaRepository
import com.example.movopfy.features.player.domain.repository.PlayerMarksRepository
import com.example.movopfy.features.player.presentation.viewmodel.PlayerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val playerModule = module {

    viewModel<PlayerViewModel> {
        PlayerViewModel(
            anilibriaRepository = get(),
            playerMarksRepository = get()
        )
    }

    single<AnilibriaRepository> {
        AnilibriaRepositoryImpl(anilibriaService = get())
    }

    single<PlayerMarksRepository> {
        PlayerMarksRepositoryImpl(playerMarksDao = get())
    }
}