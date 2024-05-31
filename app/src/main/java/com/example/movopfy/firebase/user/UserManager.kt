package com.example.movopfy.firebase.user

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth

class UserManager {

    private var auth = Firebase.auth

    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }

    fun getFirebaseAuth() = auth
}