package com.yourname.taskmanager.screen.about_screen

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.yourname.taskmanager.BuildConfig

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen() {
    val context = LocalContext.current
    val appVersion = BuildConfig.VERSION_NAME

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "ABOUT",
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                modifier = Modifier.height(64.dp),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6600FF)
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "TaskManager",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = "Version: $appVersion",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "A minimal, lightning-fast application designed to organize your thoughts, manage daily tasks, and boost your personal productivity.",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp),
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(24.dp))

            Divider()

            Text(
                text = "Made by iso-arkisto",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(vertical = 16.dp),
                color = MaterialTheme.colorScheme.primary
            )

            AboutListItem(
                title = "Source code",
                icon = Icons.Default.Build,
                onClick = { openUrl(context, "https://github.com/iso-arkisto/TaskManager") }
            )

            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Copyright (c) 2026 iso-arkisto \nLicensed under the Apache License, Version 2.0",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun AboutListItem(title: String, icon: androidx.compose.ui.graphics.vector.ImageVector, onClick: () -> Unit) {
    ListItem(
        headlineContent = { Text(title, color = MaterialTheme.colorScheme.onBackground) },
        leadingContent = { Icon(icon, contentDescription = null) },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    )
}

private fun openUrl(context: android.content.Context, url: String) {
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    } catch (e: Exception) {
        Log.d("about_screen","Failed to open url")
    }
}