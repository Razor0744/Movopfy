package com.example.movopfy.features.auth.data.repository

import android.app.Activity
import com.example.movopfy.features.auth.domain.models.CreateUserResult
import com.example.movopfy.features.auth.domain.models.SignInUserResult
import com.example.movopfy.features.auth.domain.repository.FirebaseRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FirebaseRepositoryImpl : FirebaseRepository {

    private lateinit var auth: FirebaseAuth

    private lateinit var createUserResult: CreateUserResult
    private lateinit var signInUserResult: SignInUserResult

    override suspend fun createUser(
        email: String,
        password: String,
        activity: Activity
    ): CreateUserResult =
        withContext(Dispatchers.IO) {
            auth = Firebase.auth

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity) { task ->
                    createUserResult = if (task.isSuccessful) {
                        CreateUserResult.Success
                    } else {
                        CreateUserResult.Fail
                    }
                }

            createUserResult
        }

    override suspend fun signInUser(
        email: String,
        password: String,
        activity: Activity
    ): SignInUserResult = withContext(Dispatchers.IO) {
        auth = Firebase.auth

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                signInUserResult = if (task.isSuccessful) {
                    SignInUserResult.Success
                } else {
                    SignInUserResult.Fail
                }
            }

        signInUserResult
    }

    override suspend fun checkUser(): Boolean = withContext(Dispatchers.Default) {
        auth = Firebase.auth

        auth.currentUser != null
    }
}