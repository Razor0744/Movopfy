package com.example.firebase.model

sealed interface FirestoreDataNewerResult {

    data class Success(val isNewer: Boolean) : FirestoreDataNewerResult

    data class Fail(val exception: String) : FirestoreDataNewerResult
}