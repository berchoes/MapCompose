package com.example.mapcompose.usecase

import com.example.mapcompose.common.Resource
import com.example.mapcompose.domain.model.Station
import com.example.mapcompose.domain.model.Trip
import com.example.mapcompose.domain.usecase.BookTripUseCase
import com.example.mapcompose.repository.FakeStationsRepository
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

/**
 * Created by Berk Ç. on 25.04.2022.
 */
class BookTripUseCaseTest {

    private lateinit var repository: FakeStationsRepository
    private lateinit var bookTripUseCase: BookTripUseCase

    private val station = Station(
        "26.6666,36.67867",
        999,
        "stationName",
        listOf(Trip("busName", 5688, "7 AM")),
        10
    )


    @Before
    fun setup() {
        repository = FakeStationsRepository()
        bookTripUseCase = BookTripUseCase(repository)
    }



    @ExperimentalCoroutinesApi
    @Test
    fun getStations_ReturnsStationList() = runTest(UnconfinedTestDispatcher()) {
        launch {
            bookTripUseCase.invoke(station.id!!, station.trips.first().id).collect {
                when (it) {
                    is Resource.Success -> {
                        Truth.assertThat(it.data.id).isEqualTo(5688)
                    }
                    is Resource.Error -> {
                        Truth.assertThat(it.message).isNotEmpty()
                    }
                }
            }
        }
    }
}