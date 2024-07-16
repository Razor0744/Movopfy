package com.example.search.domain.repository

import com.example.search.domain.models.SearchTitle

interface KinopoiskRepository {

    suspend fun search(searchText: String): List<SearchTitle>
}