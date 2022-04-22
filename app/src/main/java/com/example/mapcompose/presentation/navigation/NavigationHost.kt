package com.example.mapcompose.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mapcompose.common.CURRENT_STATION
import com.example.mapcompose.presentation.home.HomePage
import com.example.mapcompose.presentation.trips.TripsPage

/**
 * Created by Berk Ç. on 21.04.2022.
 */

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(
            route = Screen.Home.route
        ) {
            HomePage(navController)
        }

        composable(
            route = Screen.Trips.route + "/{$CURRENT_STATION}",
            arguments = listOf(
                navArgument(CURRENT_STATION) {
                    type = NavType.StringType
                }
            ),
        ) {
            TripsPage(navController)
        }
    }
}