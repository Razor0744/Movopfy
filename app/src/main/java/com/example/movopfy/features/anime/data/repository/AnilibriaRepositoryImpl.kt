package com.example.movopfy.features.anime.data.repository

import com.example.movopfy.features.anime.domain.repository.AnilibriaRepository
import com.example.movopfy.network.anilibria.models.AnilibriaSchedule
import com.example.movopfy.network.anilibria.service.AnilibriaService

class AnilibriaRepositoryImpl(private val anilibriaService: AnilibriaService) :
    AnilibriaRepository {

    override suspend fun getSchedules(): List<AnilibriaSchedule> {
        val response = anilibriaService.getSchedule()

        return if (response.isSuccessful) response.body() ?: emptyList() else emptyList()
    }
}