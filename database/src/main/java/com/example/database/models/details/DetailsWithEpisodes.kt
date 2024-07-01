package com.example.database.models.details

import androidx.room.Embedded
import androidx.room.Relation

data class DetailsWithEpisodes(
    @Embedded val details: Details,
    @Relation(
        parentColumn = "title_id",
        entityColumn = "title_id"
    )
    val episodesList: List<Episodes>
)