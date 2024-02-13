package com.example.movopfy.common.mappers

import com.example.movopfy.common.extensions.getSmallImageUrl
import com.example.movopfy.features.home.domain.models.AnimeSeries
import com.example.movopfy.network.anilibria.models.AnilibriaSchedule

fun mapAnilibriaUrlToImageUrl(anilibriaSchedule: AnilibriaSchedule): List<AnimeSeries> {
    return anilibriaSchedule.list.map { item ->
        AnimeSeries(id = item.id, pictureUrl = item.getSmallImageUrl())
    }
}