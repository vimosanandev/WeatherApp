package com.vimosanan.weatherapp.presentation.feature.home.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vimosanan.weatherapp.presentation.ui.theme.WeatherAppTheme

@Composable
fun HomeScreen() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                "Home",
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview(){
    WeatherAppTheme {
        HomeScreen()
    }
}