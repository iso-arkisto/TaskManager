package com.yourname.taskmanager.screen.shopping_list_screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yourname.taskmanager.dialog.MainDialog
import com.yourname.taskmanager.ui.theme.LightText
import com.yourname.taskmanager.utils.UIEvent

@Composable
fun ShoppingListScreen(
    viewModel: ShoppingListViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit
) {
    val itemsList = viewModel.list.collectAsState(emptyList())

    LaunchedEffect(true) {
        viewModel.uiEvent.collect {
            when(it) {
                is UIEvent.Navigate -> {
                    onNavigate(it.route)
                }
                else -> {}
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(top = 30.dp)
    ) {
        items(itemsList.value) {
            item ->
            UiShoppingListItem(item = item, onEvent = {
                event ->
                viewModel.onEvent(event = event)
            })
        }
    }

    MainDialog(
        dialogController = viewModel
    )

    if(itemsList.value.isEmpty()) {
        Text(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight(),
            text = "Empty list",
            textAlign = TextAlign.Center,
            fontSize = 25.sp
//            color = LightText
        )
    }


}