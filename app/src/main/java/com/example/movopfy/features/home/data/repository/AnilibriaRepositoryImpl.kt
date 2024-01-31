package com.example.movopfy.features.home.data.repository

import com.example.movopfy.features.home.domain.repository.AnilibriaRepository
import com.example.movopfy.network.anilibria.models.AnilibriaSchedule
import com.example.movopfy.network.anilibria.service.AnilibriaService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AnilibriaRepositoryImpl(private val anilibriaService: AnilibriaService) :
    AnilibriaRepository {

    override suspend fun getSchedule(): List<AnilibriaSchedule> = withContext(Dispatchers.IO) {
        val response = anilibriaService.getSchedule()
        if (response.isSuccessful) response.body() as List<AnilibriaSchedule> else emptyList()
    }
}