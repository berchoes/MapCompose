package com.example.mapcompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.mapcompose.presentation.home.HomePage
import com.example.mapcompose.presentation.navigation.NavigationHost
import com.example.mapcompose.presentation.navigation.Screen
import com.example.mapcompose.util.theme.MapComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MapComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainPage()
                }
            }
        }
    }


    @Composable
    fun MainPage() {
        val navController = rememberNavController()

        Box(modifier = Modifier.fillMaxSize()) {
            NavigationHost(navController = navController)
        }
    }
}