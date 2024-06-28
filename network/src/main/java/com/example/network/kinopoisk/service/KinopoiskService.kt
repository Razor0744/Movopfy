package com.example.network.kinopoisk.service

import com.example.network.BuildConfig
import com.example.network.kinopoisk.models.KinopoiskList
import com.example.network.kinopoisk.models.KinopoiskSearch
import com.example.network.kinopoisk.models.KinopoiskTitle
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

const val LIMIT = 20

interface KinopoiskService {

    @GET("movie")
    @Headers("accept: application/json", "X-API-KEY: ${BuildConfig.KINOPOISK_API_KEY}")
    suspend fun getList(
        @Query("page") page: Int,
        @Query("limit") limit: Int = LIMIT,
        @Query("selectFields") selectFields: List<String> = listOf("id", "poster"),
        @Query("genres.name") category: String
    ): Response<KinopoiskList>

    @GET("movie/{id}")
    @Headers("accept: application/json", "X-API-KEY: ${BuildConfig.KINOPOISK_API_KEY}")
    suspend fun getTitle(@Path("id") id: Int): Response<KinopoiskTitle>

    @GET("movie/search")
    @Headers("accept: application/json", "X-API-KEY: ${BuildConfig.KINOPOISK_API_KEY}")
    suspend fun searchTitles(
        @Query("page") page: Int,
        @Query("limit") limit: Int = LIMIT,
        @Query("query") query: String
    ) : Response<KinopoiskSearch>
}