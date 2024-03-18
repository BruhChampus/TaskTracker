package com.example.tasktracker.domain

import com.example.tasktracker.data.model.TaskCard

class TaskCardsSorter {
    /**
    Get task cards with isDone = true and return ArrayList.
    Main reason why this method created is because of Query that gets all task cards with isDone = true doesn`t work as needed,
    problem is TaskCardWithScheduledDate class gets all taskCard with corresponding date even where isDone = false.
     */
    fun getAllDoneTaskCardsFromList(taskCardsList: List<TaskCard>): ArrayList<TaskCard> {
        val doneTasks = ArrayList<TaskCard>()
        taskCardsList.forEach { taskCard ->
            if (taskCard.isDone) {
                doneTasks.add(taskCard)
            }
        }
        return doneTasks
    }

    fun getAllNotDoneTaskCardsFromList(taskCardsList: List<TaskCard>): ArrayList<TaskCard> {
        val notDoneTasks = ArrayList<TaskCard>()
        taskCardsList.forEach { taskCard ->
            if (!taskCard.isDone) {
                notDoneTasks.add(taskCard)
            }
        }
        return notDoneTasks
    }
}