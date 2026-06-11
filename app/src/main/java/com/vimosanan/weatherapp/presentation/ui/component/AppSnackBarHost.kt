package com.vimosanan.weatherapp.presentation.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vimosanan.weatherapp.core.snackbar.SnackBarController
import com.vimosanan.weatherapp.core.snackbar.SnackBarItem
import kotlinx.coroutines.delay

@Composable
fun AppSnackBarHost(modifier: Modifier = Modifier) {
    val items by SnackBarController.items.collectAsStateWithLifecycle()

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items.forEach { item ->
                SnackBarEntry(item = item)
            }
        }
    }
}

@Composable
private fun SnackBarEntry(item: SnackBarItem) {
    LaunchedEffect(item.id) {
        if (item.autoMiss) {
            delay(4_000L)
            SnackBarController.dismiss(item.id)
        }
    }

    AnimatedVisibility(
        visible = item.isVisible,
        enter = slideInVertically(animationSpec = tween(300), initialOffsetY = { -it }) +
            fadeIn(animationSpec = tween(300)),
        exit = slideOutVertically(animationSpec = tween(300), targetOffsetY = { -it }) +
            fadeOut(animationSpec = tween(300)),
    ) {
        AppToast(
            title = item.title,
            description = item.description,
            type = item.type,
            onDismiss = { SnackBarController.dismiss(item.id) },
        )
    }
}
