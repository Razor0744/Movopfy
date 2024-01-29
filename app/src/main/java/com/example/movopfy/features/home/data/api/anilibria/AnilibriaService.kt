package com.example.movopfy.features.home.data.api.anilibria

import com.example.movopfy.features.home.data.models.AnilibriaSchedule
import retrofit2.Response
import retrofit2.http.GET

interface AnilibriaService {

    @GET("title/schedule")
    suspend fun getSchedule(): Response<List<AnilibriaSchedule>>
}