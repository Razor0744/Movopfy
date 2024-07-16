package com.example.search.domain.repository

import com.example.search.domain.models.SearchTitle

interface AnilibriaRepository {

    suspend fun search(searchText: String): List<SearchTitle>
}