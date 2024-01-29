package com.example.movopfy.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

class RetrofitClient {

    private val contentType = "application/json".toMediaType()

    @OptIn(ExperimentalSerializationApi::class)
    val json = Json {
        isLenient = true
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    fun getClient(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }
}