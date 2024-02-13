package com.example.movopfy.common.mappers

import com.example.movopfy.common.extensions.getSmallImageUrl
import com.example.movopfy.features.home.domain.models.WaitingListToday
import com.example.movopfy.network.anilibria.models.AnilibriaSchedule

fun mapAnilibriaUrlToImageUrl(anilibriaSchedule: AnilibriaSchedule): List<WaitingListToday> {
    return anilibriaSchedule.list.map { item ->
        WaitingListToday(id = item.id, pictureUrl = item.getSmallImageUrl())
    }
}