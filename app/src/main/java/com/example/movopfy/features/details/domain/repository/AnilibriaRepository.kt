package com.example.movopfy.features.details.domain.repository

import com.example.movopfy.features.details.domain.models.DetailsData

interface AnilibriaRepository {

    suspend fun getTitle(id: Int, dateTime: Int): DetailsData?
}