package com.example.movopfy.features.auth.data.repository

import android.app.Activity
import com.example.movopfy.features.auth.domain.models.CreateUserResult
import com.example.movopfy.features.auth.domain.models.SignInUserResult
import com.example.movopfy.features.auth.domain.repository.FirebaseRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class FirebaseRepositoryImpl : FirebaseRepository {

    private lateinit var auth: FirebaseAuth

    override suspend fun createUser(
        email: String,
        password: String,
        activity: Activity
    ): CreateUserResult = suspendCancellableCoroutine {
        auth = Firebase.auth

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    it.resume(CreateUserResult.Success)
                } else {
                    it.resume(CreateUserResult.Fail(exception = task.exception?.message.toString()))
                }
            }
    }

    override suspend fun signInUser(
        email: String,
        password: String,
        activity: Activity
    ): SignInUserResult = suspendCancellableCoroutine {
        auth = Firebase.auth

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    it.resume(SignInUserResult.Success)
                } else {
                    it.resume(value = SignInUserResult.Fail(exception = task.exception?.message.toString()))
                }
            }
    }

    override fun checkUser(): Boolean {
        auth = Firebase.auth

        return auth.currentUser != null
    }
}