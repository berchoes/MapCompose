package com.example.mapcompose.presentation.home.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mapcompose.domain.model.Station
import com.example.mapcompose.util.convertToLatLng
import com.example.mapcompose.util.theme.MAP_STYLE_JSON
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState

/**
 * Created by Berk Ç. on 21.04.2022.
 */

@Composable
fun MapView(
    stations: List<Station> = emptyList(),
    onMarkerClicked: (Station) -> Unit = {},
    onInfoWindowClosed: (Station) -> Unit = {}
) {
    val mapStartPosition = LatLng(41.071833, 29.030358)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(mapStartPosition, 11F)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        properties = MapProperties(mapStyleOptions = MapStyleOptions(MAP_STYLE_JSON)),
        cameraPositionState = cameraPositionState,
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
                    title = "${(station.tripsCount ?: 0)} trips"
                )
            }
        }
    }
}