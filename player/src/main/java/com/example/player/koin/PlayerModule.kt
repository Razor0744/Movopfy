package com.example.player.koin

import com.example.player.data.repository.AnilibriaRepositoryImpl
import com.example.player.data.repository.PlayerMarksRepositoryImpl
import com.example.player.domain.repository.AnilibriaRepository
import com.example.player.domain.repository.PlayerMarksRepository
import com.example.player.presentation.viewmodel.PlayerViewModel
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