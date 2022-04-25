package com.example.mapcompose.presentation.trips

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mapcompose.common.BOOKED_TRIP
import com.example.mapcompose.presentation.home.components.ErrorDialog
import com.example.mapcompose.presentation.navigation.Screen
import com.example.mapcompose.presentation.trips.components.TripListItem

/**
 * Created by Berk Ç. on 21.04.2022.
 */


@Composable
fun TripsPage(
    navController: NavController,
    viewModel: TripsViewModel = hiltViewModel()
) {
    val bookResult = viewModel.bookTripResult
    val showDialog = viewModel.isDialogVisible

    if (viewModel.station != null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                viewModel.station?.trips?.let { tripList ->
                    items(tripList) {
                        TripListItem(trip = it, onBookClicked = { trip ->
                            viewModel.bookTrip(trip.id)
                        })
                    }
                }
            }
        }
    }

    if (viewModel.isLoading) {
        Box(modifier = Modifier
            .fillMaxSize()
            .clickable {}) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

    if (showDialog) {
        ErrorDialog(errorMessage = viewModel.errorMessage, onDismissed = {
            viewModel.showDialog(false)
        })
    }

    if (bookResult != null) {
        navController.previousBackStackEntry?.savedStateHandle?.set(
            BOOKED_TRIP,
            viewModel.convertToJson(bookResult)
        )
        navController.popBackStack(Screen.Home.route, false)
    }

}