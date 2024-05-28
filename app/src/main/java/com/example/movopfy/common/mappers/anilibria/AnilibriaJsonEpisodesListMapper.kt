package com.example.movopfy.common.mappers.anilibria

import com.example.movopfy.network.anilibria.models.AnilibriaEpisodesList
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

private val json = Json { ignoreUnknownKeys = true }

fun mapToAnilibriaEpisodesList(jsonElement: JsonElement?): List<AnilibriaEpisodesList> {
    return when (jsonElement) {
        is JsonObject -> convertJsonObject(jsonObject = jsonElement)

        is JsonArray -> convertJsonArray(jsonArray = jsonElement)

        else -> {
            emptyList()
        }
    }
}

private fun convertJsonArray(jsonArray: JsonArray): List<AnilibriaEpisodesList> {
    return jsonArray.map { jsonElement ->
        json.decodeFromJsonElement(AnilibriaEpisodesList.serializer(), jsonElement)
    }
}

private fun convertJsonObject(jsonObject: JsonObject): List<AnilibriaEpisodesList> {
    val list = arrayListOf<AnilibriaEpisodesList>()

    for (i in 1..jsonObject.keys.size) {
        list.add(
            json.decodeFromJsonElement(
                AnilibriaEpisodesList.serializer(),
                jsonObject["$i"] ?: return emptyList()
            )
        )
    }

    return list
}