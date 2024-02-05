package com.example.movopfy.network.kinopoisk.service

import com.example.movopfy.network.kinopoisk.models.KinopoiskList
import com.example.movopfy.network.kinopoisk.models.KinopoiskTitle
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface KinopoiskService {

    @GET("movie")
    @Headers("accept: application/json", "X-API-KEY: B7MW9FS-1BQM1N0-H4W3YZ7-3FAEB2K")
    suspend fun getList(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("selectFields") selectFields: List<String> = listOf("id", "poster")
    ): Response<KinopoiskList>

    @GET("movie/{id}")
    @Headers("accept: application/json", "X-API-KEY: B7MW9FS-1BQM1N0-H4W3YZ7-3FAEB2K")
    suspend fun getTitle(@Path("id") id: Int): Response<KinopoiskTitle>
}