package com.example.movopfy.features.player.data.repository

import com.example.movopfy.features.player.domain.repository.AnilibriaRepository
import com.example.movopfy.network.anilibria.models.AnilibriaTitle
import com.example.movopfy.network.anilibria.service.AnilibriaService

class AnilibriaRepositoryImpl(private val anilibriaService: AnilibriaService) :
    AnilibriaRepository {

    override suspend fun getTitle(id: Int): AnilibriaTitle? {
        val response = anilibriaService.getTitle(id = id)

        return if (response.isSuccessful) response.body() else null
    }
}