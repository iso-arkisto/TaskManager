package com.yourname.taskmanager.screen.note_list_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yourname.taskmanager.R
import com.yourname.taskmanager.data.entity.NoteItem
import com.yourname.taskmanager.ui.theme.LightText
import com.yourname.taskmanager.utils.Routes
import com.yourname.taskmanager.utils.toDateTimeString

@Composable
fun UiNoteItem(
    item: NoteItem,
    event: (NoteListEvent) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 3.dp, top = 3.dp, end = 3.dp)
            .clickable {
                event(NoteListEvent.OnItemClick(Routes.NEW_NOTE + "/${item.id}"))
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = item.title,
                    modifier = Modifier
                        .padding(top = 10.dp, start = 10.dp)
                        .weight(1f),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = item.updatedAt.toDateTimeString(),
                    modifier = Modifier
                        .padding(top = 10.dp, end = 10.dp),
                    fontSize = 12.sp
                )
            }

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = item.description,
                        modifier = Modifier
                            .weight(1f)
                            .padding(top = 10.dp, start = 10.dp),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        color = LightText,
                        fontSize = 12.sp
                    )
                    IconButton(
                        onClick = {
                            event(NoteListEvent.OnShowDeleteDialog(item))
                        },
                        modifier = Modifier.padding(bottom = 10.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.delete),
                            contentDescription = "Delete",
                            tint = Color.Red
                        )
                    }
                }
            }
        }

}

@Preview(showBackground = true)
@Composable
fun PrevNoteItem() {
    val note = NoteItem(
        title = "TITLE",
        description = "DESCRIPTIONWDHGWDGHAJDGQGEWEDYWEFUGDFQGDSHAKJDAGDEWGDWDHDJHAJHD",
        id = 13278378,
        createdAt = System.currentTimeMillis()
    )
    UiNoteItem(
        item = note,
        event = {}
    )
}