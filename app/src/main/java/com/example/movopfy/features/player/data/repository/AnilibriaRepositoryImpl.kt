package com.example.movopfy.features.player.data.repository

import com.example.common.extensions.getUrl
import com.example.common.mappers.anilibria.mapToAnilibriaEpisodesList
import com.example.movopfy.features.player.domain.models.PlayerData
import com.example.movopfy.features.player.domain.repository.AnilibriaRepository
import com.example.network.anilibria.models.AnilibriaTitle
import com.example.network.anilibria.service.AnilibriaService

class AnilibriaRepositoryImpl(private val anilibriaService: com.example.network.anilibria.service.AnilibriaService) :
    AnilibriaRepository {

    private var anilibriaTitle: com.example.network.anilibria.models.AnilibriaTitle? = null

    override suspend fun getPlayerData(id: Int, episode: Int): PlayerData {

        return when {
            anilibriaTitle != null && id == anilibriaTitle?.id -> {
                PlayerData(
                    url = com.example.common.mappers.anilibria.mapToAnilibriaEpisodesList(
                        anilibriaTitle?.player?.list
                    )[episode].hls?.getUrl()
                        ?: "",
                    episodesCount = com.example.common.mappers.anilibria.mapToAnilibriaEpisodesList(
                        anilibriaTitle?.player?.list
                    ).size - 1
                )
            }

            else -> {
                val response = anilibriaService.getTitle(id = id)

                anilibriaTitle = if (response.isSuccessful) response.body() else null

                PlayerData(
                    url = com.example.common.mappers.anilibria.mapToAnilibriaEpisodesList(
                        anilibriaTitle?.player?.list
                    )[episode].hls?.getUrl()
                        ?: "",
                    episodesCount = com.example.common.mappers.anilibria.mapToAnilibriaEpisodesList(
                        anilibriaTitle?.player?.list
                    ).size - 1
                )
            }
        }
    }
}