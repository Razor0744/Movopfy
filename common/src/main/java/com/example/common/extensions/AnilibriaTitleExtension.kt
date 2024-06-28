package com.example.common.extensions

import com.example.network.anilibria.models.AnilibriaTitle

fun AnilibriaTitle.getSmallImageUrl() = anilibriaPosters?.small?.url?.let {
    "https://www.anilibria.tv$it"
}