package com.example.movopfy.features.auth.domain.models

sealed interface SignInUserResult {

    data class Fail(val exception: String) : SignInUserResult

    data object Success : SignInUserResult
}