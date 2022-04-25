package com.example.mapcompose.presentation.trips

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mapcompose.common.SavedLists
import com.example.mapcompose.domain.model.Trip
import com.example.mapcompose.util.theme.DarkBlue
import com.example.mapcompose.util.theme.DarkGreen

/**
 * Created by Berk Ç. on 22.04.2022.
 */

@Composable
fun TripListItem(
    trip: Trip,
    onBookClicked: (Trip) -> Unit
) {
    val isBooked = SavedLists.everyBookedTripIds.contains(trip.id)

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = trip.busName,
            color = Color.Black,
            style = MaterialTheme.typography.h1,
            fontSize = 14.sp
        )

        Row(
            modifier = Modifier.wrapContentWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = trip.time,
                color = Color.Black,
                style = MaterialTheme.typography.h1,
                modifier = Modifier.padding(horizontal = 14.dp),
                fontSize = 14.sp
            )
            Button(
                onClick = { if (!isBooked) onBookClicked(trip) },
                shape = RoundedCornerShape(40.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (isBooked) DarkGreen else DarkBlue
                )
            ) {
                Text(
                    text = if(isBooked) "Booked" else "Book",
                    color = Color.White,
                    fontSize = 12.sp,
                    style = MaterialTheme.typography.h1,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }
    Divider(color = Color.LightGray.copy(alpha = 0.4F))
}