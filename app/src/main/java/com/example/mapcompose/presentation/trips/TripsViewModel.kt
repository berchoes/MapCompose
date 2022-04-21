package com.example.mapcompose.presentation.trips

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.mapcompose.base.BaseViewModel
import com.example.mapcompose.common.CURRENT_STATION
import com.example.mapcompose.domain.model.Station
import com.example.mapcompose.domain.usecase.BookTripUseCase
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Created by Berk Ç. on 21.04.2022.
 */

@HiltViewModel
class TripsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val bookTripUseCase: BookTripUseCase
) : BaseViewModel() {

    var station: Station? = null
        private set
        get() {
            val json = savedStateHandle.get<String>(CURRENT_STATION)
            json?.let {
                field = convertJsonToStation(it)
                return field
            }
            return field
        }

    private fun bookTrip(tripId: Int) {
        station?.id?.let { stationId ->
            bookTripUseCase.invoke(stationId, tripId).onEach {

            }.launchIn(viewModelScope)
        }
    }


    private fun convertJsonToStation(json: String): Station {
        return Gson().fromJson(json, Station::class.java)
    }
}