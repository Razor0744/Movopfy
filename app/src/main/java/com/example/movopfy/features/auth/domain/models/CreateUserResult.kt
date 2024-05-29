package com.example.movopfy.features.auth.domain.models

sealed interface CreateUserResult {

    data class Fail(val exception: String) : CreateUserResult

    data object Success : CreateUserResult
}