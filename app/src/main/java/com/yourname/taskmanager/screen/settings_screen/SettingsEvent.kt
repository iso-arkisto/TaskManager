package com.yourname.taskmanager.screen.settings_screen

sealed class SettingsEvent {
    data class OnItemSelected(
        val color: String
    ): SettingsEvent()
}