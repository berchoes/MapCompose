package com.example.mapcompose.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.base.BaseViewModel
import com.example.mapcompose.common.Resource
import com.example.mapcompose.domain.usecase.BookTripUseCase
import com.example.mapcompose.domain.usecase.GetStationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Created by Berk Ç. on 20.04.2022.
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getStationsUseCase: GetStationsUseCase,
    private val bookTripUseCase: BookTripUseCase
) : ViewModel() {

    var homePageState by mutableStateOf(HomePageState())
        private set

    init {
        getStations()
    }

    private fun getStations() {
        getStationsUseCase.invoke().onEach {
            homePageState = when (it) {
                is Resource.Error -> HomePageState(errorMessage = it.message)
                Resource.Loading -> HomePageState(isLoading = true)
                is Resource.Success -> HomePageState(stations = it.data)
            }
        }.launchIn(viewModelScope)
    }
}