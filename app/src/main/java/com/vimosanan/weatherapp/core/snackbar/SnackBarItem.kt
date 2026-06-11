package com.vimosanan.weatherapp.core.snackbar

import com.vimosanan.weatherapp.presentation.ui.component.ToastType
import java.util.UUID

data class SnackBarItem(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String? = null,
    val type: ToastType = ToastType.ERROR,
    val autoMiss: Boolean = true,
    val isVisible: Boolean = false,
)
