package com.yourname.taskmanager.screen.settings_screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yourname.taskmanager.R
import com.yourname.taskmanager.ui.theme.TaskManagerTheme

@Composable
fun UiColorItem(
    item: ColorItem,
    onEvent: (SettingsEvent) -> Unit
) {
    IconButton(
        onClick = {
            onEvent(SettingsEvent.OnItemSelected(item.color))
        },
        modifier = Modifier
            .padding(start = 10.dp)
            .clip(CircleShape)
            .size(35.dp)
            .background(
                color = Color(
                    android.graphics.Color.parseColor(
                        item.color
                    )
                )
            )
    ) {
        if (item.isSelected) {
            Icon(
                painter = painterResource(id = R.drawable.check),
                contentDescription = "check",
                tint = Color.White
            )
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun UiColorItemPreview() {
    TaskManagerTheme(darkTheme = true) {
        UiColorItem(
            item = ColorItem(
                color = "#FFA8A8FF",
                isSelected = true
            ),
            onEvent = {}
        )
    }
}