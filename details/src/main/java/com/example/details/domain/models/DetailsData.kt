package com.example.details.domain.models

import com.example.network.anilibria.models.AnilibriaEpisodesList

data class DetailsData(
    val url: String?,
    val name: String?,
    val description: String?,
    val episodesList: List<com.example.network.anilibria.models.AnilibriaEpisodesList>?
)
