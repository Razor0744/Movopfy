package com.example.movopfy.features.home.data.api.anilibria

import com.example.movopfy.features.home.data.models.AnilibriaSchedule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Anilibria(private val anilibriaURL: AnilibriaURL) {

//    suspend fun getSchedule(): List<AnilibriaSchedule> = suspendCoroutine {
//        anilibriaURL.anilibriaService.getSchedule().enqueue(object :
//            retrofit2.Callback<List<AnilibriaSchedule>> {
//            override fun onResponse(
//                call: Call<List<AnilibriaSchedule>>,
//                response: Response<List<AnilibriaSchedule>>
//            ) {
//                it.resume(response.body() as List<AnilibriaSchedule>)
//            }
//
//            override fun onFailure(call: Call<List<AnilibriaSchedule>>, t: Throwable) {
//                println(t)
//            }
//        })
//    }

    suspend fun getSchedule(): List<AnilibriaSchedule> = withContext(Dispatchers.IO) {
        val response = anilibriaURL.anilibriaService.getSchedule()
        if (response.isSuccessful) response.body() as List<AnilibriaSchedule> else emptyList()
    }
}