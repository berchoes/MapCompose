package com.example.mapcompose.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mapcompose.common.BOOKED_TRIP
import com.example.mapcompose.presentation.home.components.ErrorDialog
import com.example.mapcompose.presentation.home.components.MapView
import com.example.mapcompose.presentation.navigation.Screen
import com.example.mapcompose.util.theme.DarkBlue


/**
 * Created by Berk Ç. on 20.04.2022.
 */

@Composable
fun HomePage(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val selectedStation = viewModel.selectedStation

    val bookedTrip =
        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>(BOOKED_TRIP)
            ?.observeAsState()

    Box(modifier = Modifier.fillMaxSize()) {

        MapView(
            stations = viewModel.stations,
            onMarkerClicked = {
                viewModel.setTappedStation(it)
            },
            onInfoWindowClosed = {
                viewModel.setTappedStation(null)
            })

        if (selectedStation != null) {
            Button(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 24.dp, vertical = 30.dp)
                    .fillMaxWidth(),
                onClick = {
                    navController.navigate(
                        Screen.Trips.route + "/${
                            viewModel.convertToJson(
                                selectedStation
                            )
                        }"
                    )
                },
                shape = RoundedCornerShape(40.dp),
                colors = ButtonDefaults.buttonColors(contentColor = DarkBlue)

            ) {
                Text(
                    text = "List Trips",
                    color = Color.White,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(vertical = 4.dp),
                    style = MaterialTheme.typography.h1
                )
            }
        }

        if (viewModel.isLoading) {
            Box(modifier = Modifier
                .fillMaxSize()
                .clickable {}) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        if (viewModel.isDialogVisible) {
            ErrorDialog(errorMessage = viewModel.errorMessage)
        }

        if (bookedTrip?.value != null) {
            val bookedTripObject = viewModel.convertJsonToBookResponse(bookedTrip.value!!)
            bookedTripObject.stationId?.let { viewModel.setBookedStation(it) }
        }
    }
}