package com.example.mapcompose.presentation.home.components

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.example.mapcompose.R
import com.example.mapcompose.domain.model.Station
import com.example.mapcompose.presentation.home.HomeViewModel
import com.example.mapcompose.util.bitmapDescriptor
import com.example.mapcompose.util.convertToLatLng
import com.example.mapcompose.util.theme.MAP_STYLE_JSON
import com.google.android.gms.maps.model.*
import com.google.maps.android.compose.*

/**
 * Created by Berk Ç. on 21.04.2022.
 */

@Composable
fun MapView(
    stations: List<Station> = emptyList(),
    onMarkerClicked: (Station) -> Unit = {},
    onInfoWindowClosed: (Station) -> Unit = {},
) {
    val mapStartPosition = LatLng(41.061173, 29.062033)
    val context = LocalContext.current
    val uiSettings = remember { MapUiSettings(zoomControlsEnabled = false) }

    val icon = bitmapDescriptor(context, R.drawable.point)
    val completedIcon = bitmapDescriptor(context, R.drawable.completed)
    val selectedIcon = bitmapDescriptor(context, R.drawable.selected_point)


    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(mapStartPosition, 11F)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        properties = MapProperties(mapStyleOptions = MapStyleOptions(MAP_STYLE_JSON)),
        cameraPositionState = cameraPositionState,
        uiSettings = uiSettings
    ) {
        if (stations.isNotEmpty()) {
            stations.forEach { station ->
                Marker(
                    position = station.centerCoordinates.convertToLatLng(),
                    onInfoWindowClose = {
                        onInfoWindowClosed(station)
                    },
                    onClick = {
                        it.showInfoWindow()
                        onMarkerClicked(station)
                        true
                    },
                    title = "${(station.tripsCount ?: 0)} trips",
                    icon = if (station.isSelected) selectedIcon else if(station.isBooked) completedIcon else icon,
                )
            }
        }
    }
}
