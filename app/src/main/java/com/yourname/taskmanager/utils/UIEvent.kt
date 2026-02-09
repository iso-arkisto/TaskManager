package com.yourname.taskmanager.utils

sealed class UIEvent {
    object PopBackStack: UIEvent()

    data class Navigate(
        val route: String
    ): UIEvent()

    data class ShowSnackBar(
        val text: String
    ): UIEvent()
}