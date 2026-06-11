package com.vimosanan.weatherapp.presentation.feature.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import com.vimosanan.weatherapp.R
import com.vimosanan.weatherapp.domain.model.Weather
import com.vimosanan.weatherapp.presentation.preview.WeatherPreviewData
import com.vimosanan.weatherapp.presentation.ui.theme.WeatherAppTheme

@Composable
fun WeatherContent(weather: Weather) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = weather.city.orEmpty(),
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.W500
                ),
            )
            weather.condition?.description?.let { description ->
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.W400
                    ),
                )
            }
        }

        weather.main?.let { main ->
            TemperatureArcCard(
                temp = main.temp ?: 0f,
                tempMin = main.tempMin ?: 0f,
                tempMax = main.tempMax ?: 0f,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            WeatherInfoCard(
                iconRes = R.drawable.icon_wind,
                title = "WIND",
                value = "${weather.wind?.speed ?: "-"} MPH",
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
            )
            WeatherInfoCard(
                iconRes = R.drawable.icon_feels_like,
                title = "FEELS LIKE",
                value = "${weather.main?.feelsLike ?: "-"}°F",
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
private fun WeatherContentPreview() {
    WeatherAppTheme {
        WeatherContent(weather = WeatherPreviewData.weather)
    }
}
