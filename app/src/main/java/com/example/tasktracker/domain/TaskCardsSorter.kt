package com.example.tasktracker.domain

import com.example.tasktracker.data.model.TaskCard

class TaskCardsSorter {
    /**
    Get task cards with isDone = true and return ArrayList.
    The reason why this method created is because of Query that gets all task cards with isDone = true doesnt work,
    the reason is TaskCardWithScheduledDate class.
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