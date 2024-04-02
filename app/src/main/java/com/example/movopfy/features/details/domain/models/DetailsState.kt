package com.example.movopfy.features.details.domain.models

import com.example.movopfy.database.models.favourite.FavouriteModel

data class DetailsState(
    val detailsData: DetailsData?,
    val favouriteModel: FavouriteModel?
)