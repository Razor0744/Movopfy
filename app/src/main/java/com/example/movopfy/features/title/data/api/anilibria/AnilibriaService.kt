package com.example.movopfy.features.title.data.api.anilibria

import com.example.movopfy.features.title.data.models.AnilibriaTitle
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AnilibriaService {

    @GET("title")
    suspend fun getTitle(@Query("id") id: Int): Response<AnilibriaTitle>
}