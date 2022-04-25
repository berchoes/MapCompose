package com.example.mapcompose.repository

import com.example.mapcompose.data.dto.BookResponseDto
import com.example.mapcompose.data.dto.StationDto
import com.example.mapcompose.data.dto.TripDto
import com.example.mapcompose.domain.repository.StationsRepository

/**
 * Created by Berk Ç. on 25.04.2022.
 */
class FakeStationsRepository : StationsRepository {

    private val stations = listOf(
        StationDto(
            "26.6666,36.67867",
            999,
            "stationName",
            listOf(TripDto("busName", 5688, "7 AM")),
            10
        )
    )

    private val bookResponse = BookResponseDto("busName", 5688, "7 AM")

    override suspend fun getStations(): List<StationDto> = stations

    override suspend fun bookTrip(stationId: Int, tripId: Int): BookResponseDto = bookResponse
}