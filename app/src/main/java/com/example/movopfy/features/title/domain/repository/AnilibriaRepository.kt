package com.example.movopfy.features.title.domain.repository

import com.example.movopfy.features.title.data.models.AnilibriaTitle

interface AnilibriaRepository {

    suspend fun getTitle(id: Int): AnilibriaTitle?
}