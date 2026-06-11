package com.vimosanan.weatherapp.presentation.feature.home.event

import com.vimosanan.weatherapp.presentation.ui.component.ToastType

sealed class HomeUiEvent {
    data class ShowSnackBar(
        val title: String,
        val description: String? = null,
        val type: ToastType = ToastType.ERROR,
    ) : HomeUiEvent()
}
