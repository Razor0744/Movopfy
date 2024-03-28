package com.example.movopfy.features.details.domain.repository

import com.example.movopfy.features.details.domain.models.DetailsState

interface AnilibriaRepository {

    suspend fun getTitle(id: Int): DetailsState?
}