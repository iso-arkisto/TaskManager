package com.yourname.taskmanager.screen.note_list_screen

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.UiMode
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yourname.taskmanager.data.entity.NoteItem
import com.yourname.taskmanager.dialog.MainDialog
import com.yourname.taskmanager.ui.theme.BlueLight
import com.yourname.taskmanager.ui.theme.GrayLightSoft
import com.yourname.taskmanager.ui.theme.Purple40
import com.yourname.taskmanager.ui.theme.TaskManagerTheme
import com.yourname.taskmanager.utils.UIEvent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NoteListScreen(
    viewModel: NoteListViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit
) {
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(true) {
        viewModel.uiEvent.collect { uIEvent ->
            when(uIEvent) {
                is UIEvent.Navigate -> {
                    onNavigate(uIEvent.route)
                }
                is UIEvent.ShowSnackBar -> {
                    val result = scaffoldState.snackbarHostState.showSnackbar(
                        message = uIEvent.text,
                        actionLabel = "Cancel"
                    )
                    if(result == SnackbarResult.ActionPerformed) {
                        viewModel.onEvent(NoteListEvent.OnCancelItem)
                    }

                }
                else -> {}
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = {
            SnackbarHost(
                hostState = scaffoldState.snackbarHostState
            ) { data ->
                Snackbar(
                    snackbarData = data,
                    backgroundColor = Purple40,
                    modifier = Modifier.padding(bottom = 50.dp)
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(GrayLightSoft)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                shape = RoundedCornerShape(15.dp)

            ) {
                TextField(
                    value = viewModel.searchQuery,
                    onValueChange = { viewModel.onEvent(NoteListEvent.OnTextSearchChange(it)) },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Search...") }
                )

            }

            if(viewModel.noteList.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 100.dp)
                ) {
                    items(viewModel.noteList) { note ->
                        UiNoteItem(
                            item = note,
                            event = viewModel::onEvent
                        )
                    }
                }
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Empty",
                        textAlign = TextAlign.Center,
                        fontSize = 25.sp,
                        color = Color.Gray
                    )
                }
            }

            MainDialog(dialogController = viewModel)
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NoteListContent(
    searchQuery: String,
    noteList: List<NoteItem>,
    onEvent: (NoteListEvent) -> Unit
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = {
            SnackbarHost(
                hostState = scaffoldState.snackbarHostState
            ) { data ->
                Snackbar(
                    snackbarData = data,
                    backgroundColor = Purple40,
                    modifier = Modifier.padding(bottom = 50.dp)
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(GrayLightSoft)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                shape = RoundedCornerShape(15.dp)

            ) {
                TextField(
                    value = searchQuery,
                    onValueChange = { onEvent(NoteListEvent.OnTextSearchChange(it)) },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Search...") },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedLabelColor = BlueLight,
                        unfocusedLabelColor = Color.Gray
                    )
                )

            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 100.dp)
            ) {
                items(noteList) { note ->
                    UiNoteItem(
                        item = note,
                        event = onEvent
                    )
                }
            }

            if(noteList.isEmpty()) {
                Text(
                    text = "Empty",
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxSize(),
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp,
                    color = Color.LightGray
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    name = "Light mode"
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark mode"
)
@Composable
fun PreviewScreen() {
    TaskManagerTheme {
        NoteListContent(
            searchQuery = "note",
            onEvent = {},
            noteList = listOf(
                NoteItem(
                    id = 23,
                    title = "Tasks",
                    description = "Lorem ipsum",
                    time = "23.06.2023 12:34"
                )
            )
        )
    }
}