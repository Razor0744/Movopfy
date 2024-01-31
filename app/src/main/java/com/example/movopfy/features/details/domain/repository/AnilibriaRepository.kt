package com.example.movopfy.features.details.domain.repository

import com.example.movopfy.network.anilibria.models.AnilibriaTitle

interface AnilibriaRepository {

    suspend fun getTitle(id: Int): AnilibriaTitle?
}