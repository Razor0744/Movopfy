package com.example.movopfy.firebase.koin

import com.example.movopfy.firebase.synchronization.SynchronizationFavourites
import com.example.movopfy.firebase.user.UserManager
import org.koin.dsl.module

val firebaseModule = module {

    single<UserManager> {
        UserManager()
    }

    single<SynchronizationFavourites> {
        SynchronizationFavourites(userManager = get())
    }
}