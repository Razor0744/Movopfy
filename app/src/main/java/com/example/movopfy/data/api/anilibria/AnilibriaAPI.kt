package com.example.movopfy.data.api.anilibria

import com.example.movopfy.domain.models.AnilibriaSchedule
import retrofit2.Call
import retrofit2.http.GET

interface AnilibriaAPI {

    @GET("title/schedule?days=2")
    fun getSchedule(): Call<List<AnilibriaSchedule>>

}