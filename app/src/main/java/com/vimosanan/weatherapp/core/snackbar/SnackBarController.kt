package com.vimosanan.weatherapp.core.snackbar

import com.vimosanan.weatherapp.presentation.ui.component.ToastType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

object SnackBarController {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private val _items = MutableStateFlow<List<SnackBarItem>>(emptyList())
    val items: StateFlow<List<SnackBarItem>> = _items.asStateFlow()

    fun show(
        title: String,
        description: String? = null,
        type: ToastType = ToastType.ERROR,
        autoMiss: Boolean = true,
    ) {
        val item = SnackBarItem(title = title, description = description, type = type, autoMiss = autoMiss, isVisible = false)
        addItem(item)
    }

    private fun addItem(item: SnackBarItem) {
        _items.update { it + item }
        scope.launch {
            delay(16)
            _items.update { list ->
                list.map { if (it.id == item.id) it.copy(isVisible = true) else it }
            }
        }
    }

    fun dismiss(id: String) {
        _items.update { list ->
            list.map { if (it.id == id) it.copy(isVisible = false) else it }
        }
        scope.launch {
            delay(350)
            _items.update { it.filter { item -> item.id != id } }
        }
    }
}
