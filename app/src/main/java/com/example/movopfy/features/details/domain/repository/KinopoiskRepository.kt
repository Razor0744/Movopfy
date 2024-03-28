package com.example.movopfy.features.details.domain.repository

import com.example.movopfy.features.details.domain.models.DetailsState

interface KinopoiskRepository {

    suspend fun getTitle(id: Int): DetailsState?
}