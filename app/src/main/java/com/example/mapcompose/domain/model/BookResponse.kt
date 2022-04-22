package com.example.mapcompose.domain.model

/**
 * Created by Berk Ç. on 20.04.2022.
 */
data class BookResponse(
    val busName: String,
    val id: Int?,
    val time: String,
    var stationId: Int? = null,
)
