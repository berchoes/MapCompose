package com.example.mapcompose.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mapcompose.presentation.home.components.CustomDialog
import com.example.mapcompose.presentation.home.components.MapView
import com.example.mapcompose.presentation.navigation.Screen


/**
 * Created by Berk Ç. on 20.04.2022.
 */

@Composable
fun HomePage(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {

    Box(modifier = Modifier.fillMaxSize()) {

        MapView(viewModel.stations, onMarkerClicked = {
            navController.navigate(Screen.Trips.route + "/${viewModel.convertToJson(it)}")
        })

        if (viewModel.isLoading) {
            Box(modifier = Modifier
                .fillMaxSize()
                .clickable {}) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        if (viewModel.errorMessage != null) {
            CustomDialog(
                error = viewModel.errorMessage
            )
        }
    }
}