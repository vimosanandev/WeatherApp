package com.vimosanan.weatherapp.presentation.feature.home.component

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.os.LocaleListCompat
import com.vimosanan.weatherapp.presentation.ui.theme.WeatherAppTheme

@Composable
fun LanguageButton(modifier: Modifier = Modifier) {
    var currentTag by remember {
        mutableStateOf(
            AppCompatDelegate.getApplicationLocales()
                .toLanguageTags()
                .takeIf { it.isNotEmpty() }
                ?: java.util.Locale.getDefault().language,
        )
    }

    val isSpanish = currentTag.startsWith("es")
    val label = if (isSpanish) "EN" else "ES"

    TextButton(
        onClick = {
            val newTag = if (isSpanish) "en" else "es"
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(newTag))
            currentTag = newTag
        },
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color.White.copy(alpha = 0.6f),
                shape = RoundedCornerShape(8.dp),
            )
            .padding(horizontal = 2.dp)
            .height(44.dp),
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = Color.White,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF6B4EE6)
@Composable
private fun LanguageButtonPreview() {
    WeatherAppTheme {
        LanguageButton()
    }
}
