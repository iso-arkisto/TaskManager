package com.yourname.taskmanager.screen.shopping_list_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.yourname.taskmanager.R
import com.yourname.taskmanager.ui.theme.DarkText
import com.yourname.taskmanager.ui.theme.LightText
import com.yourname.taskmanager.ui.theme.Pink40
import com.yourname.taskmanager.ui.theme.PinkPastel

@Composable
@Preview(showBackground = true)
fun UiShoppingListItem() {
    ConstraintLayout(
        modifier = Modifier
            .padding(start = 3.dp, top = 18.dp, end = 3.dp)
    ) {
        val (card, deleteBtn, editBtn, counterBtn) = createRefs()
        
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(card) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .clickable {

                } // onEvent
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
            ) {
                Text(
                    text = "List 1",
                    style = TextStyle(
                        color = DarkText,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                )
                Text(
                    text = "02/02/2026 18:51",
                    style = TextStyle(
                        color = LightText,
                        fontSize = 12.sp
                    )
                )
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    progress = .5f
                )
            }
        }


        IconButton(
            modifier = Modifier
                .padding(end = 5.dp)
                .size(30.dp)
                .constrainAs(editBtn) {
                    top.linkTo(card.top)
                    bottom.linkTo(card.top)
                    end.linkTo(deleteBtn.start)
                },
            onClick = {} // ShoppingList Event
        ) {
            Icon(
                painter = painterResource(R.drawable.edit),
                contentDescription = "Edit",
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.Blue)
                    .padding(5.dp),
                tint = Color.White
            )
        }

        IconButton(
            modifier = Modifier
                .padding(end = 10.dp)
                .size(30.dp)
                .constrainAs(deleteBtn) {
                    top.linkTo(card.top)
                    bottom.linkTo(card.top)
                    end.linkTo(card.end)
                },
            onClick = {} // ShoppingList Event
        ) {
            Icon(
                painter = painterResource(R.drawable.delete),
                contentDescription = "Delete",
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.Red)
                    .padding(5.dp),
                tint = Color.White
            )
        }

        Card(
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .constrainAs(counterBtn) {
                    top.linkTo(card.top)
                    bottom.linkTo(card.top)
                    end.linkTo(editBtn.start)
                }
                .padding(end = 5.dp)
        ) {
            Text(
                text = "1/6",
                color = Color.White,
                modifier = Modifier
                    .background(Color.Green)
                    .padding(top = 3.dp, bottom = 3.dp, start = 5.dp, end = 5.dp)
            )
        }


    }
}