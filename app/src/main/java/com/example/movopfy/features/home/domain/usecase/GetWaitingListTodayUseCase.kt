package com.example.movopfy.features.home.domain.usecase

import com.example.movopfy.features.home.domain.models.WaitingListToday
import com.example.movopfy.features.home.domain.repository.AnilibriaRepository

class GetWaitingListTodayUseCase(private val anilibriaRepository: AnilibriaRepository) {

    suspend fun execute(day: Int): List<WaitingListToday> {
        val schedule = anilibriaRepository.getSchedule()

        return schedule[day].list.map { item ->
            val pictureUrl = item.anilibriaPosters?.small?.url.let {
                "https://www.anilibria.tv$it"
            }
            WaitingListToday(id = item.id, pictureUrl = pictureUrl)
        }
    }
}