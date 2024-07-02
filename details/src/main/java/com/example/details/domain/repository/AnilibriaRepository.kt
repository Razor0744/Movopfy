package com.example.details.domain.repository

import com.example.details.domain.models.DetailsData

interface AnilibriaRepository {

    suspend fun getTitle(id: Int, dateTime: Int): DetailsData?
}