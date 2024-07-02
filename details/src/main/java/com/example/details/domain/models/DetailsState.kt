package com.example.details.domain.models

import com.example.database.models.favourite.FavouriteModel

data class DetailsState(
    val detailsData: DetailsData?,
    val favouriteModel: FavouriteModel?
)