package com.example.movopfy.data.api.anilibria

import com.example.movopfy.data.api.RetrofitClient
import com.example.movopfy.domain.models.AnilibriaSchedule
import retrofit2.Call
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class Anilibria(private val retrofitClient: RetrofitClient) {

    suspend fun getSchedule(): List<AnilibriaSchedule> = suspendCoroutine {
        retrofitClient.anilibriaAPI.getSchedule().enqueue(object :
            retrofit2.Callback<List<AnilibriaSchedule>> {
            override fun onResponse(
                call: Call<List<AnilibriaSchedule>>,
                response: Response<List<AnilibriaSchedule>>
            ) {
                it.resume(response.body() as List<AnilibriaSchedule>)
            }

            override fun onFailure(call: Call<List<AnilibriaSchedule>>, t: Throwable) {
                println(t)
            }
        })
    }
}