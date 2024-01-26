package com.example.movopfy.features.home.data.repository

import com.example.movopfy.features.home.data.api.anilibria.Anilibria
import com.example.movopfy.features.home.data.models.AnilibriaSchedule
import com.example.movopfy.features.home.domain.repository.AnilibriaRepository

class AnilibriaRepositoryImpl(private val anilibria: Anilibria) : AnilibriaRepository{
    override suspend fun getSchedule(): List<AnilibriaSchedule> {
        return anilibria.getSchedule()
    }

}