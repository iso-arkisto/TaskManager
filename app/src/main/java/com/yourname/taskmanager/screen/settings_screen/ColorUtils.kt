package com.yourname.taskmanager.screen.settings_screen

import androidx.compose.ui.graphics.Color

object ColorUtils {
    val colorList = listOf(
        "#FFB388FF",
        "#FF82B1FF",
        "#FF80D8FF",
        "#FFFF80AB",
        "#FFE680C8",
        "#FF80C8FF",
        "#FFA8A8FF",
        "#FFFF80FF",
        "#FFFFFF80",
        "#FFFFD080",
        "#FF80FFA8",
        "#FFFFB080",
        "#FFFFA8FF",
        "#FFA8FF80"
    )

    fun getProgressColor(progress: Float): Color {
        return when(progress) {
            in 0.0..0.339 -> Color.Red
            in 0.34..0.669 -> Color.Yellow
            in 0.67..1.0 -> Color.Green
            else -> Color.Red
        }
    }
}