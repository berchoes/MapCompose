package com.example.mapcompose.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mapcompose.R
import com.example.mapcompose.domain.model.Station
import com.example.mapcompose.util.bitmapDescriptor
import com.example.mapcompose.util.convertToLatLng
import com.example.mapcompose.util.theme.MAP_STYLE_JSON
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
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
                val tripCount = station.tripsCount ?: 0

                MarkerInfoWindow(
                    state = MarkerState(position = station.centerCoordinates.convertToLatLng()),
                    onInfoWindowClose = {
                        onInfoWindowClosed(station)
                    },
                    onClick = {
                        it.showInfoWindow()
                        onMarkerClicked(station)
                        true
                    },
                    icon = if (station.isSelected) selectedIcon else if (station.isBooked) completedIcon else icon,
                    content = {
                        Column(Modifier.padding(start = 4.dp)) {
                            Box(
                                modifier = Modifier
                                    .background(
                                        color = Color.DarkGray,
                                        shape = RoundedCornerShape(3.dp),
                                    )
                                    .padding(horizontal = 12.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "$tripCount " + if (tripCount == 1) "trip" else "trips",
                                    fontSize = 12.sp,
                                    style = MaterialTheme.typography.body1,
                                    color = Color.White
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .align(alignment = Alignment.CenterHorizontally)
                                    .size(height = 8.dp, width = 8.dp)
                                    .clip(
                                        CutCornerShape(bottomEnd = 8.dp)
                                    )
                                    .background(color = Color.DarkGray)
                            )
                        }
                    }
                )
            }
        }
    }
}
