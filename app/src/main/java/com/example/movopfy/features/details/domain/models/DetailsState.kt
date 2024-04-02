package com.example.movopfy.features.details.domain.models

import com.example.movopfy.database.models.favourite.Favourite

data class DetailsState(
    val detailsData: DetailsData?,
    val favourite: Favourite?
)