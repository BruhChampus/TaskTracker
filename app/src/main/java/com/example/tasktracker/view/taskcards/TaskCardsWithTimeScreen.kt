package com.example.tasktracker.view.taskcards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.tasktracker.Utils
import com.example.tasktracker.data.model.TaskCardWithScheduledDate
import com.example.tasktracker.domain.MyTimeConverter
import com.example.tasktracker.domain.TaskCardsSorter


@Composable
fun TaskCardsWithTime(list: Set<TaskCardWithScheduledDate>) {
    LazyColumn {
        list.forEachIndexed { index, taskCardWithScheduledDate ->
            val doneTaskCardsList = TaskCardsSorter().getAllDoneTaskCardsFromList(taskCardWithScheduledDate.taskCardsList)
            if (doneTaskCardsList.isNotEmpty()) {
                item {
                    Column {
                        Row {
                            Divider(
                                thickness = 2.dp,
                                color = Color.Gray,
                                modifier = Modifier
                                    .weight(1f)
                                    .align(Alignment.CenterVertically)
                            )
                            Text(
                                text = MyTimeConverter.transformMillisToDate(taskCardWithScheduledDate.taskCardScheduledDate.dateInMillis),
                                color = Color.Gray,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                textAlign = TextAlign.Center
                            )
                            Divider(
                                thickness = 2.dp,
                                color = Color.Gray,
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .weight(1f)
                            )
                        }
                    }
                }

                items(doneTaskCardsList.size) {
                    TaskCardItem(
                        taskCard = doneTaskCardsList[it],
                        showTimeInCard = true
                    ) {}
                }
            }
        }
    }
}
