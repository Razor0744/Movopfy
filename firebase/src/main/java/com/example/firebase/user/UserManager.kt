package com.example.firebase.user

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth

class UserManager {

    private var auth = Firebase.auth

    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }

    fun getFirebaseAuth() = auth

    fun logOut() = auth.signOut()

    fun getUID(): String = auth.uid ?: ""
}