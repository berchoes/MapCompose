package com.example.mapcompose.data.dto


import com.example.mapcompose.domain.model.Trip
import com.google.gson.annotations.SerializedName

data class TripDto(
    @SerializedName("bus_name")
    val busName: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("time")
    val time: String?
)


fun TripDto.toTripModel() = Trip(
    busName = this.busName ?: "noBusName",
    id = this.id,
    time = this.time ?: "noTimeSpecified"
)