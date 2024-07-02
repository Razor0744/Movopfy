package com.example.auth.domain.repository

import android.app.Activity
import com.example.auth.domain.models.CreateUserResult
import com.example.auth.domain.models.SignInUserResult

interface FirebaseRepository {

    suspend fun createUser(email: String, password: String, activity: Activity): CreateUserResult

    suspend fun signInUser(email: String, password: String, activity: Activity): SignInUserResult
}