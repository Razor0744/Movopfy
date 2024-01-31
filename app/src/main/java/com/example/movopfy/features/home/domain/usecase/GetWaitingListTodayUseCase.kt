package com.example.movopfy.features.home.domain.usecase

import com.example.movopfy.common.extensions.getSmallImageUrl
import com.example.movopfy.features.home.domain.models.WaitingListToday
import com.example.movopfy.features.home.domain.repository.AnilibriaRepository

class GetWaitingListTodayUseCase(private val anilibriaRepository: AnilibriaRepository) {

    suspend fun execute(day: Int): List<WaitingListToday> {
        val schedule = anilibriaRepository.getSchedule()[day]

        return schedule.list.map { item ->
            WaitingListToday(id = item.id, pictureUrl = item.getSmallImageUrl())
        }
    }
}