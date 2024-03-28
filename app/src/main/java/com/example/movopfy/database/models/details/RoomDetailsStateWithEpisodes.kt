package com.example.movopfy.database.models.details

import androidx.room.Embedded
import androidx.room.Relation

data class RoomDetailsStateWithEpisodes(
    @Embedded val detailsState: RoomDetailsState,
    @Relation(
        parentColumn = "title_id",
        entityColumn = "title_id"
    )
    val episodesList: List<RoomEpisodes>
)