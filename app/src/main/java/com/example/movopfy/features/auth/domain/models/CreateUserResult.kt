package com.example.movopfy.features.auth.domain.models

sealed interface CreateUserResult {

    data object Fail : CreateUserResult

    data object Success : CreateUserResult
}