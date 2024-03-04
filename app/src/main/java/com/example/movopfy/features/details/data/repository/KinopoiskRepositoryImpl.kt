package com.example.movopfy.features.details.data.repository

import com.example.movopfy.features.details.domain.repository.KinopoiskRepository
import com.example.movopfy.network.kinopoisk.models.KinopoiskTitle
import com.example.movopfy.network.kinopoisk.service.KinopoiskService

class KinopoiskRepositoryImpl(private val kinopoiskService: KinopoiskService): KinopoiskRepository {

    override suspend fun getTitle(id: Int): KinopoiskTitle? {
        val response =  kinopoiskService.getTitle(id = id)

        return if (response.isSuccessful) response.body() else null
    }
}