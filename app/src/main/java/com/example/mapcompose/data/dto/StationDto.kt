package com.example.mapcompose.data.dto


import com.example.mapcompose.domain.model.Station
import com.google.gson.annotations.SerializedName

data class StationDto(
    @SerializedName("center_coordinates")
    val centerCoordinates: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("trips")
    val trips: List<TripDto>?,
    @SerializedName("trips_count")
    val tripsCount: Int?
)

fun StationDto.toStationModel() = Station(
    centerCoordinates = this.centerCoordinates ?: "noCoordinates",
    id = this.id,
    name = this.name ?: "noName",
    trips = this.trips?.map { it.toTripModel() } ?: emptyList(),
    tripsCount = this.tripsCount
)