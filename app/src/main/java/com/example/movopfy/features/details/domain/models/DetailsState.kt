package com.example.movopfy.features.details.domain.models

import com.example.movopfy.network.anilibria.models.AnilibriaEpisodesList

data class DetailsState(
    val url: String?,
    val name: String?,
    val description: String?,
    val episodesList: List<AnilibriaEpisodesList>?
)