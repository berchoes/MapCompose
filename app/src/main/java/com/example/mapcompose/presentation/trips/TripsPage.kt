package com.example.mapcompose.presentation.trips

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.unit.dp
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
    
    if(viewModel.station != null){
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            LazyColumn(modifier = Modifier.fillMaxSize()){
                viewModel.station?.trips?.let { tripList ->
                    items(tripList){
                        TripListItem(trip = it, onBookClicked = {

                        })
                    }
                }
            }
        }
    }
   
}