package com.example.movopfy.features.home.data.api.anilibria

import com.example.movopfy.retrofit.RetrofitClient

class AnilibriaURL(private val retrofitClient: RetrofitClient) {

    private val anilibriaUrl = "https://api.anilibria.tv/v3/"
    val anilibriaService: AnilibriaService
        get() = retrofitClient.getClient(anilibriaUrl).create(AnilibriaService::class.java)
}