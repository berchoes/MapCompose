package com.example.mapcompose.presentation.navigation

/**
 * Created by Berk Ç. on 21.04.2022.
 */
sealed class Screen(val route: String) {
    object Home : Screen(route = "home_screen")
    object Trips : Screen(route = "trips_screen")
}
