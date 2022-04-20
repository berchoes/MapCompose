package com.example.mapcompose.domain.repository

import com.example.mapcompose.data.dto.BookResponseDto
import com.example.mapcompose.data.dto.StationDto

/**
 * Created by Berk Ç. on 20.04.2022.
 */
interface StationsRepository {

    suspend fun getStations(): List<StationDto>
    suspend fun bookTrip(stationId: Int, tripId: Int): BookResponseDto
}