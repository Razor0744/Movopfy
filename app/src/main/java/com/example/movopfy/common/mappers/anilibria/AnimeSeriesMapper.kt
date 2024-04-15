package com.example.movopfy.common.mappers.anilibria

import com.example.movopfy.common.extensions.getSmallImageUrl
import com.example.movopfy.common.models.AnimeSeries
import com.example.movopfy.network.anilibria.models.AnilibriaSchedule

fun mapToAnimeSeriesList(anilibriaSchedule: AnilibriaSchedule): List<AnimeSeries>? {
    return anilibriaSchedule.list?.map { item ->
        AnimeSeries(id = item.id ?: 0, pictureUrl = item.getSmallImageUrl() ?: "")
    }
}