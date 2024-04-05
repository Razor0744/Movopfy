package com.example.movopfy.features.search.domain.repository

import com.example.movopfy.features.search.domain.models.SearchTitle

interface AnilibriaRepository {

    suspend fun search(searchText: String): List<SearchTitle>
}