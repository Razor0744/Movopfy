package com.example.movopfy.features.title.data.repository

import com.example.movopfy.features.title.data.api.anilibria.AnilibriaService
import com.example.movopfy.features.title.data.models.AnilibriaTitle
import com.example.movopfy.features.title.domain.repository.AnilibriaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AnilibriaRepositoryImpl(private val anilibriaService: AnilibriaService) :
    AnilibriaRepository {

    override suspend fun getTitle(id: Int): AnilibriaTitle? = withContext(Dispatchers.IO) {
        val response = anilibriaService.getTitle(id = id)
        if (response.isSuccessful) response.body() as AnilibriaTitle else null
    }
}