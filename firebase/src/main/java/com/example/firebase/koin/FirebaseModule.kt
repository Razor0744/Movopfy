package com.example.firebase.koin

import com.example.firebase.synchronization.FirestoreFavourites
import com.example.firebase.user.UserManager
import org.koin.dsl.module

val firebaseModule = module {

    single<UserManager> {
        UserManager()
    }

    single<FirestoreFavourites> {
        FirestoreFavourites(userManager = get())
    }
}