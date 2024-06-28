package com.example.common.extensions

import com.example.network.anilibria.models.AnilibriaHls

fun AnilibriaHls.getUrl() = fhd.let {
    "https://cache.libria.fun$it"
}
