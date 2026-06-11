package com.vimosanan.weatherapp.presentation.feature.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.vimosanan.weatherapp.presentation.feature.home.screen.HomeScreen
import com.vimosanan.weatherapp.presentation.navigation.Screen

fun NavGraphBuilder.homeNavGraph() {
    composable(route = Screen.Home.route) {
        HomeScreen()
    }
}
