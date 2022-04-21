package com.example.mapcompose.domain.model

/**
 * Created by Berk Ç. on 20.04.2022.
 */
data class Station(
    val centerCoordinates: String,
    val id: Int?,
    val name: String,
    val trips: List<Trip>,
    val tripsCount: Int?
)
