package com.example.mapcompose.presentation.trips

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

/**
 * Created by Berk Ç. on 21.04.2022.
 */


@Composable
fun TripsPage(
    navController: NavController,
    viewModel: TripsViewModel = hiltViewModel()
) {
    Box(modifier = Modifier.fillMaxSize()) {
        println(viewModel.station?.trips?.get(0).toString())
    }
}