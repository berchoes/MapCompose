package com.example.mapcompose.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.mapcompose.base.BaseViewModel
import com.example.mapcompose.common.Resource
import com.example.mapcompose.domain.model.Station
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
    private val getStationsUseCase: GetStationsUseCase
) : BaseViewModel() {

    var stations by mutableStateOf<List<Station>>(emptyList())
        private set

    init {
        getStations()
    }

    private fun getStations() {
        isLoading = true
        getStationsUseCase.invoke().onEach {
            when (it) {
                is Resource.Error -> {
                    errorMessage = it.message
                }
                is Resource.Success -> {
                    stations = it.data
                }
            }
            isLoading = false
        }.launchIn(viewModelScope)
    }
}