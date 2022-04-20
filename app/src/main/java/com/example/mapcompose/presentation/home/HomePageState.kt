package com.example.mapcompose.presentation.home

import com.example.mapcompose.domain.model.Station

/**
 * Created by Berk Ç. on 20.04.2022.
 */
data class HomePageState(
    var isLoading: Boolean = false,
    var stations: List<Station> = emptyList(),
    var errorMessage: String? = null
)
