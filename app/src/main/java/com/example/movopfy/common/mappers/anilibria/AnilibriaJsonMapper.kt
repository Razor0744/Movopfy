package com.example.movopfy.common.mappers.anilibria

import android.util.Log
import com.example.movopfy.network.anilibria.models.AnilibriaEpisodesList
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

const val JSON_ERROR = "JsonError"
private val json = Json { ignoreUnknownKeys = true }

fun mapToAnilibriaEpisodesList(jsonElement: JsonElement): List<AnilibriaEpisodesList> {
    return when (jsonElement) {
        is JsonObject -> convertJsonObject(jsonObject = jsonElement)

        is JsonArray -> convertJsonArray(jsonArray = jsonElement)

        else -> {
            Log.e(JSON_ERROR, "mapAnilibriaJsonToDataClass: cannot convert Json")
            emptyList()
        }
    }
}

fun convertJsonArray(jsonArray: JsonArray): List<AnilibriaEpisodesList> {
    return jsonArray.map { jsonElement ->
        json.decodeFromJsonElement(AnilibriaEpisodesList.serializer(), jsonElement)
    }
}

fun convertJsonObject(jsonObject: JsonObject): List<AnilibriaEpisodesList> {
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