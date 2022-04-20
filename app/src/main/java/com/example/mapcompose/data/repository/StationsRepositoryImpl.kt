package com.example.mapcompose.data.repository

import com.example.mapcompose.data.dto.BookResponseDto
import com.example.mapcompose.data.dto.StationDto
import com.example.mapcompose.data.service.StationsApi
import com.example.mapcompose.domain.repository.StationsRepository
import javax.inject.Inject

/**
 * Created by Berk Ç. on 20.04.2022.
 */
class StationsRepositoryImpl @Inject constructor(private val api: StationsApi): StationsRepository {

    override suspend fun getStations(): List<StationDto> = api.getStations()

    override suspend fun bookTrip(stationId: Int, tripId: Int): BookResponseDto = api.bookTrip(stationId,tripId)
}