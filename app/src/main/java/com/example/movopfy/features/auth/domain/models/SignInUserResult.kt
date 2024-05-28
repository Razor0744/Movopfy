package com.example.movopfy.features.auth.domain.models

sealed interface SignInUserResult {

    data object Fail : SignInUserResult

    data object Success : SignInUserResult
}