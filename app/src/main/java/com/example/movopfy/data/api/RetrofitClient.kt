package com.example.movopfy.data.api

import com.example.movopfy.data.api.anilibria.AnilibriaAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val ANILIBRIA_URL = "https://api.anilibria.tv/v3/"
    val anilibriaAPI: AnilibriaAPI
        get() = getClient(ANILIBRIA_URL).create(AnilibriaAPI::class.java)


    private var retrofit: Retrofit? = null
    private fun getClient(baseUrl: String): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}