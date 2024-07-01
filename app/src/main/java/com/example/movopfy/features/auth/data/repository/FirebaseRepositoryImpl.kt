package com.example.movopfy.features.auth.data.repository

import android.app.Activity
import android.util.Log
import com.example.movopfy.features.auth.domain.models.CreateUserResult
import com.example.movopfy.features.auth.domain.models.SignInUserResult
import com.example.movopfy.features.auth.domain.repository.FirebaseRepository
import com.example.firebase.user.UserManager
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class FirebaseRepositoryImpl(private val userManager: com.example.firebase.user.UserManager) : FirebaseRepository {

    override suspend fun createUser(
        email: String,
        password: String,
        activity: Activity
    ): CreateUserResult = suspendCancellableCoroutine {
        userManager.getFirebaseAuth().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                Log.i("firebase", "createUser: $task")
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
        userManager.getFirebaseAuth().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                Log.i("firebase", "createUser: $task")
                if (task.isSuccessful) {
                    it.resume(SignInUserResult.Success)
                } else {
                    it.resume(SignInUserResult.Fail(exception = task.exception?.message.toString()))
                }
            }
    }
}