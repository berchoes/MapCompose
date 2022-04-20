package com.example.mapcompose.domain.model

import com.example.mapcompose.data.dto.TripDto

/**
 * Created by Berk Ç. on 20.04.2022.
 */
data class Station(
    val centerCoordinates: String,
    val id: Int?,
    val name: String,
    val trips: List<TripDto>,
    val tripsCount: Int?
)
