package com.vimosanan.weatherapp.presentation.feature.home.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vimosanan.weatherapp.R
import com.vimosanan.weatherapp.presentation.ui.theme.WeatherAppTheme

@Composable
fun WeatherInfoCard(
    @DrawableRes iconRes: Int,
    title: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = title,
                modifier = Modifier.size(32.dp),
            )
            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}

@Preview(name = "Wind")
@Composable
private fun WeatherInfoWindCardPreview() {
    WeatherAppTheme {
        WeatherInfoCard(
            iconRes = R.drawable.icon_wind,
            title = "WIND",
            value = "4.12 MPH",
        )
    }
}

@Preview(name = "Feels Like")
@Composable
private fun WeatherInfoFeelsLikeCardPreview() {
    WeatherAppTheme {
        WeatherInfoCard(
            iconRes = R.drawable.icon_feels_like,
            title = "FEELS LIKE",
            value = "24°F",
        )
    }
}

