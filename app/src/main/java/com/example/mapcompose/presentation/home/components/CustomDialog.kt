package com.example.mapcompose.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/**
 * Created by Berk Ç. on 21.04.2022.
 */


@Composable
fun CustomDialog(
    error: String? = null,
    onDismissed: () -> Unit = {},
) {
    AlertDialog(
        onDismissRequest = {},
        confirmButton = {
            Button(onClick = onDismissed,Modifier.background(color = Color.Blue)) {
                Text(text = "Okay", color = Color.White)
            }
        },
        text = {
            Text(
                text = error ?: "",
            )
        },
        backgroundColor = Color.White
    )
}