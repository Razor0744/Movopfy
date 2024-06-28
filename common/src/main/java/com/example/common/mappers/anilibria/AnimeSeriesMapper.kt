package com.example.common.mappers.anilibria

import com.example.common.extensions.getSmallImageUrl
import com.example.common.models.AnimeSeries
import com.example.network.anilibria.models.AnilibriaSchedule

fun mapToAnimeSeriesList(anilibriaSchedule: AnilibriaSchedule): List<AnimeSeries>? {
    return anilibriaSchedule.list?.map { item ->
        AnimeSeries(id = item.id ?: 0, pictureUrl = item.getSmallImageUrl() ?: "")
    }
}