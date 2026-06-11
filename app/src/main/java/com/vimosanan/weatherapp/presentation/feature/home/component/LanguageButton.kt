package com.vimosanan.weatherapp.presentation.feature.home.component

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.res.painterResource
import com.vimosanan.weatherapp.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.os.LocaleListCompat
import com.vimosanan.weatherapp.presentation.feature.home.model.LanguageOption
import com.vimosanan.weatherapp.presentation.ui.theme.WeatherAppTheme

@Composable
fun LanguageButton(modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    var currentTag by remember {
        mutableStateOf(
            AppCompatDelegate.getApplicationLocales()
                .toLanguageTags()
                .takeIf { it.isNotEmpty() }
                ?: java.util.Locale.getDefault().language,
        )
    }

    Box(modifier = modifier) {
        IconButton(onClick = { expanded = true }) {
            Icon(
                painter = painterResource(R.drawable.ic_language),
                contentDescription = null,
                tint = Color.White,
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            LanguageOption.entries.forEach { option ->
                val isSelected = currentTag.startsWith(option.tag)
                DropdownMenuItem(
                    text = {
                        Text(
                            text = option.displayName,
                            style = if (isSelected) {
                                MaterialTheme.typography.bodyMedium.copy(
                                    color = MaterialTheme.colorScheme.primary,
                                )
                            } else {
                                MaterialTheme.typography.bodyMedium
                            },
                        )
                    },
                    onClick = {
                        expanded = false
                        if (!isSelected) {
                            AppCompatDelegate.setApplicationLocales(
                                LocaleListCompat.forLanguageTags(option.tag),
                            )
                            currentTag = option.tag
                        }
                    },
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF6B4EE6)
@Composable
private fun LanguageButtonPreview() {
    WeatherAppTheme {
        LanguageButton()
    }
}
