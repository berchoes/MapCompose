package com.example.mapcompose.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel


/**
 * Created by Berk Ç. on 20.04.2022.
 */

@Composable
fun HomePage(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val homePageState = viewModel.homePageState

    Box(modifier = Modifier.fillMaxSize()){
        if (homePageState.stations.isNotEmpty()) {
            println(homePageState.stations.first().centerCoordinates)
        }

        if (homePageState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}