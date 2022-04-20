package com.example.mapcompose.data.service

import com.example.mapcompose.data.dto.BookResponseDto
import com.example.mapcompose.data.dto.StationDto
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Created by Berk Ç. on 20.04.2022.
 */
interface StationsApi {

    @GET("stations")
    suspend fun getStations(): List<StationDto>

    @POST("stations/{stationId}/trips/{tripId}")
    suspend fun bookTrip(@Path("stationId") sid: Int, @Path("tripId") tid: Int): BookResponseDto
}