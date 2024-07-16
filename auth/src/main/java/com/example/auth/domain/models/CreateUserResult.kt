package com.example.auth.domain.models

sealed interface CreateUserResult {

    data class Fail(val exception: String) : CreateUserResult

    data object Success : CreateUserResult
}