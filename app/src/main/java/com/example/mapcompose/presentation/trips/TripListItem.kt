package com.example.mapcompose.presentation.trips

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mapcompose.domain.model.Trip
import com.example.mapcompose.util.theme.DarkBlue

/**
 * Created by Berk Ç. on 22.04.2022.
 */

@Composable
fun TripListItem(
    trip: Trip,
    onBookClicked: (Trip) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = trip.busName, color = Color.Black, style = MaterialTheme.typography.h1)

        Row(
            modifier = Modifier.wrapContentWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = trip.time,
                color = Color.Black,
                style = MaterialTheme.typography.h1,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            Button(
                onClick = { onBookClicked(trip) },
                shape = RoundedCornerShape(40.dp),
                colors = ButtonDefaults.buttonColors(contentColor = DarkBlue)
            ) {
                Text(
                    text = "Book",
                    color = Color.White,
                    style = MaterialTheme.typography.h1,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }
    Divider(color = Color.LightGray)
}