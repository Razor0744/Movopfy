package com.example.movopfy.network.anilibria.service

import com.example.movopfy.network.anilibria.models.AnilibriaSchedule
import com.example.movopfy.network.anilibria.models.AnilibriaSearch
import com.example.movopfy.network.anilibria.models.AnilibriaTitle
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val Limit = 10

interface AnilibriaService {

    @GET("title/schedule")
    suspend fun getSchedule(): Response<List<AnilibriaSchedule>>

    @GET("title")
    suspend fun getTitle(@Query("id") id: Int): Response<AnilibriaTitle>

    @GET("title/search")
    suspend fun searchTitles(
        @Query("search") searchText: String,
        @Query("limit") limit: Int = Limit
    ): Response<AnilibriaSearch>
}