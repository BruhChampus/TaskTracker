package com.example.tasktracker.view.taskcards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasktracker.data.model.TaskCard
import com.example.tasktracker.view.ui.theme.PurpleGrey40

@Composable
fun TaskCardView(taskCard: TaskCard, onClick:()-> Unit) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = taskCard.time,
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .align(Alignment.CenterVertically),
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        TaskCardItem(taskCard = taskCard, showTimeInCard = false, onClick)
    }
}


@Composable
fun TaskCardItem(taskCard: TaskCard, showTimeInCard: Boolean = false, onCLick:()-> Unit) {
    Row {
        Icon(
            imageVector =
            if (taskCard.isDone) {
                Icons.Default.CheckCircle
            } else {
                Icons.Default.AddCircle
            },
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .clickable {
                     onCLick()
                 }
        )
        Divider(
            thickness = 2.dp,
            color = Color.Black,
            modifier = Modifier
                .width(15.dp)
                .weight(1f)
                .align(Alignment.CenterVertically),
        )

        Card(
            modifier = Modifier
                .weight(11f)
                .padding(start = 0.dp, end = 0.dp, top = 10.dp, bottom = 10.dp),
            shape = RoundedCornerShape(15.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(taskCard.cardColor)
            ),
        ) {
            Column() {
                Text(
                    text = taskCard.title, modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.Start),
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = taskCard.content,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.Start),
                    color = PurpleGrey40,
                    fontSize = 13.sp
                )

                //Checks if need to show taskCard time below
                if (showTimeInCard) {
                    Text(
                        text = taskCard.time,
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.Start),
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
        }
        Divider(
            thickness = 2.dp,
            color = Color.Black,
            modifier = Modifier
                .weight(2f)
                .align(Alignment.CenterVertically),
        )
    }
}
