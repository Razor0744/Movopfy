package com.example.movopfy.common.mappers.anilibria

import android.util.Log
import com.example.movopfy.common.constants.JSON_ERROR
import com.example.movopfy.network.anilibria.models.AnilibriaEpisodes
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

private val json = Json { ignoreUnknownKeys = true }

fun mapToAnilibriaEpisodes(jsonElement: JsonElement): AnilibriaEpisodes? {
    return when (jsonElement) {
        is JsonObject -> convertJsonObject(jsonObject = jsonElement)

        else -> {
            Log.e(JSON_ERROR, "mapToAnilibriaEpisodes: cannot convert Json")
            null
        }
    }
}

private fun convertJsonObject(jsonObject: JsonObject): AnilibriaEpisodes {
    return json.decodeFromJsonElement(AnilibriaEpisodes.serializer(), jsonObject)
}