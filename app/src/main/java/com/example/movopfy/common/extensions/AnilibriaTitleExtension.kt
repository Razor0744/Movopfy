package com.example.movopfy.common.extensions

import com.example.movopfy.network.anilibria.models.AnilibriaTitle

fun AnilibriaTitle.getSmallImageUrl() = anilibriaPosters?.small?.url?.let {
    "https://www.anilibria.tv$it"
}