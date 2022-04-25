package com.example.mapcompose.usecase

import com.example.mapcompose.common.Resource
import com.example.mapcompose.domain.model.Station
import com.example.mapcompose.domain.usecase.GetStationsUseCase
import com.example.mapcompose.repository.FakeStationsRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

/**
 * Created by Berk Ç. on 25.04.2022.
 */
class GetStationsUseCaseTest {

    private lateinit var repository: FakeStationsRepository
    private lateinit var getStationsUseCase: GetStationsUseCase


    @Before
    fun setup() {
        repository = FakeStationsRepository()
        getStationsUseCase = GetStationsUseCase(repository)
    }


    @ExperimentalCoroutinesApi
    @Test
    fun getStations_ReturnsStationList() = runTest(UnconfinedTestDispatcher()) {
        launch {
            getStationsUseCase.invoke().collect {
                when (it) {
                    is Resource.Success -> {
                        assertThat(it.data).isNotEmpty()
                        assertThat(it.data.first()).isInstanceOf(Station::class.java)
                    }
                    is Resource.Error -> {
                        assertThat(it.message).isNotEmpty()
                    }
                }
            }
        }
    }
}