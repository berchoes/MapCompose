package com.example.mapcompose.presentation.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Created by Berk Ç. on 21.04.2022.
 */


@Composable
fun ErrorDialog(
    errorMessage: String,
    isNetworkError: Boolean = false,
    onDismissed: () -> Unit = {},
) {
    AlertDialog(
        shape = RoundedCornerShape(6.dp),
        title = {
            Text(
                text = if (isNetworkError) "Error" else "The trip you selected is full.",
                color = Color.Black,
                style = MaterialTheme.typography.h1
            )
        },
        text = {
            Text(
                text = if (isNetworkError) errorMessage else "Please select another one.",
                color = Color.Black,
                style = MaterialTheme.typography.body1
            )
        },
        onDismissRequest = {},
        backgroundColor = Color.White,
        confirmButton = {},
        dismissButton = {
            Button(
                onClick = onDismissed,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 12.dp),
                shape = RoundedCornerShape(40.dp)
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 6.dp),
                    text = if(isNetworkError) "Okay" else "Select a trip", color = Color.White
                )
            }
        }
    )
}
