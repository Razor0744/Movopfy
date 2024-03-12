package com.example.movopfy.common.extensions

import com.example.movopfy.network.anilibria.models.AnilibriaHls

fun AnilibriaHls.getUrl() = fhd.let {
    "https://cache.libria.fun$it"
}
