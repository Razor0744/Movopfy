package com.example.network.anilibria.service

import com.example.network.anilibria.models.AnilibriaSchedule
import com.example.network.anilibria.models.AnilibriaSearch
import com.example.network.anilibria.models.AnilibriaTitle
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val Limit = 10

interface AnilibriaService {

    @GET("title/schedule")
    suspend fun getSchedule(): Response<List<com.example.network.anilibria.models.AnilibriaSchedule>>

    @GET("title")
    suspend fun getTitle(@Query("id") id: Int): Response<com.example.network.anilibria.models.AnilibriaTitle>

    @GET("title/search")
    suspend fun searchTitles(
        @Query("search") searchText: String,
        @Query("limit") limit: Int = com.example.network.anilibria.service.Limit
    ): Response<com.example.network.anilibria.models.AnilibriaSearch>
}