package com.example.movopfy.features.home.domain.usecase

import com.example.movopfy.features.home.domain.models.WaitingListToday
import com.example.movopfy.features.home.domain.repository.AnilibriaRepository

class GetWaitingListTodayUseCase(private val anilibriaRepository: AnilibriaRepository) {

    suspend fun execute(day: Int): List<WaitingListToday> {
        val schedule = anilibriaRepository.getSchedule()
        val waitingListToday = arrayListOf<WaitingListToday>()

        for (i in schedule[day].list) {
            waitingListToday.add(
                WaitingListToday(
                    id = i.id,
                    pictureUrl = "https://www.anilibria.tv${i.anilibriaPosters?.small?.url}"
                )
            )
        }

        return waitingListToday
    }
}