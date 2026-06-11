package com.vimosanan.weatherapp.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vimosanan.weatherapp.presentation.ui.theme.WeatherAppTheme

enum class ToastType { SUCCESS, ERROR, INFO, WARNING }

private data class ToastStyle(
    val icon: ImageVector,
    val iconTint: Color,
    val backgroundColor: Color,
    val textColor: Color,
)

@Composable
private fun toastStyle(type: ToastType): ToastStyle = when (type) {
    ToastType.SUCCESS -> ToastStyle(
        icon = Icons.Default.CheckCircle,
        iconTint = Color(0xFF1A6B3C),
        backgroundColor = Color(0xFFDCFCE7),
        textColor = Color(0xFF1A6B3C),
    )
    ToastType.ERROR -> ToastStyle(
        icon = Icons.Default.Warning,
        iconTint = Color(0xFF9B1C1C),
        backgroundColor = Color(0xFFFEE2E2),
        textColor = Color(0xFF9B1C1C),
    )
    ToastType.WARNING -> ToastStyle(
        icon = Icons.Default.Warning,
        iconTint = Color(0xFF92400E),
        backgroundColor = Color(0xFFFEF3C7),
        textColor = Color(0xFF92400E),
    )
    ToastType.INFO -> ToastStyle(
        icon = Icons.Default.Info,
        iconTint = Color(0xFF1E40AF),
        backgroundColor = Color(0xFFDBEAFE),
        textColor = Color(0xFF1E40AF),
    )
}

@Composable
fun AppToast(
    title: String,
    description: String? = null,
    type: ToastType = ToastType.ERROR,
    onDismiss: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val style = toastStyle(type)

    Box(
        modifier = modifier
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(8.dp))
            .background(color = style.backgroundColor)
            .padding(horizontal = 12.dp, vertical = 10.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = style.icon,
                contentDescription = null,
                tint = style.iconTint,
                modifier = Modifier.size(22.dp),
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = style.textColor,
                    ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
                if (!description.isNullOrEmpty()) {
                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = style.textColor.copy(alpha = 0.8f),
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
            IconButton(
                onClick = onDismiss,
                modifier = Modifier.size(28.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Dismiss",
                    tint = style.iconTint,
                    modifier = Modifier.size(16.dp),
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 390)
@Composable
private fun AppToastErrorPreview() {
    WeatherAppTheme {
        AppToast(
            title = "Something went wrong",
            description = "Unable to fetch weather data. Please try again.",
            type = ToastType.ERROR,
        )
    }
}

@Preview(showBackground = true, widthDp = 390)
@Composable
private fun AppToastInfoPreview() {
    WeatherAppTheme {
        AppToast(
            title = "Location not found",
            type = ToastType.INFO,
        )
    }
}
