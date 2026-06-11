package com.vimosanan.weatherapp.presentation.feature.home.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vimosanan.weatherapp.presentation.preview.WeatherPreviewData
import com.vimosanan.weatherapp.presentation.ui.theme.WeatherAppTheme
import kotlin.math.cos
import kotlin.math.sin

private const val ARC_START_ANGLE = 150f
private const val ARC_SWEEP_ANGLE = 240f
private const val STROKE_WIDTH_DP = 12f

@Composable
fun TemperatureArcCard(
    temp: Float,
    tempMin: Float,
    tempMax: Float,
    modifier: Modifier = Modifier,
) {
    val trackColor = MaterialTheme.colorScheme.surfaceVariant
    val fillColor = MaterialTheme.colorScheme.primary
    val dotColor = MaterialTheme.colorScheme.primary

    val fraction = if (tempMax != tempMin) {
        ((temp - tempMin) / (tempMax - tempMin)).coerceIn(0f, 1f)
    } else 0f

    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .padding(32.dp),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val strokePx = STROKE_WIDTH_DP.dp.toPx()
            val inset = strokePx / 2f
            val arcSize = Size(size.width - strokePx, size.height - strokePx)
            val topLeft = Offset(inset, inset)

            // Track
            drawArc(
                color = trackColor,
                startAngle = ARC_START_ANGLE,
                sweepAngle = ARC_SWEEP_ANGLE,
                useCenter = false,
                topLeft = topLeft,
                size = arcSize,
                style = Stroke(width = strokePx, cap = StrokeCap.Round),
            )

            // Filled arc up to current temp
            drawArc(
                color = fillColor,
                startAngle = ARC_START_ANGLE,
                sweepAngle = ARC_SWEEP_ANGLE * fraction,
                useCenter = false,
                topLeft = topLeft,
                size = arcSize,
                style = Stroke(width = strokePx, cap = StrokeCap.Round),
            )

            // Dot at current temp position
            val dotAngleRad = Math.toRadians(
                (ARC_START_ANGLE + ARC_SWEEP_ANGLE * fraction).toDouble()
            )
            val radius = size.width / 2f
            val cx = size.width / 2f
            val cy = size.height / 2f
            val dotX = cx + (radius - strokePx / 2f) * cos(dotAngleRad).toFloat()
            val dotY = cy + (radius - strokePx / 2f) * sin(dotAngleRad).toFloat()
            drawCircle(
                color = dotColor,
                radius = strokePx * 0.8f,
                center = Offset(dotX, dotY),
            )
        }

        // Center: current temp
        Text(
            text = "${temp.toInt()}°",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )

        // Bottom-left: tempMin
        Text(
            text = "${tempMin.toInt()}°",
            style = MaterialTheme.typography.labelMedium,
            color = Color.White.copy(alpha = 0.8f),
            modifier = Modifier
                .align(Alignment.BottomStart)
                .offset(x = 8.dp),
        )

        // Bottom-right: tempMax
        Text(
            text = "${tempMax.toInt()}°",
            style = MaterialTheme.typography.labelMedium,
            color = Color.White.copy(alpha = 0.8f),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = (-8).dp),
        )
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
private fun TemperatureArcCardPreview() {
    WeatherAppTheme {
        WeatherPreviewData.weather.main?.let { main ->
            TemperatureArcCard(
                temp = main.temp ?: 0f,
                tempMin = main.tempMin ?: 0f,
                tempMax = main.tempMax ?: 0f,
            )
        }
    }
}
