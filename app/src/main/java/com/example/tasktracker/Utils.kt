package com.example.tasktracker

import android.content.Context
import android.view.Gravity
import android.widget.Toast
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.intl.Locale
import com.example.tasktracker.data.model.TaskCard
import java.text.SimpleDateFormat
import java.util.Calendar
import kotlin.random.Random

object Utils{

    //Transforms time from m to mm representation
    fun transformTime(time:String):String{
        val regex = Regex("""(\d+)""")
        val matchResult = regex.find(time)
        var minute = ""
        if (matchResult != null) {
            minute = matchResult.groupValues[1].padStart(2, '0')
        }
        return minute
    }

    fun transformMillisToDate(timeInMillis:Long): String {
        val dateFormatter =
            SimpleDateFormat("dd MMMM yyyy", java.util.Locale.getDefault())
           return dateFormatter.format(timeInMillis)
    }

    fun showToast(context: Context, msg:String){
        val toast = Toast.makeText(
            context,
            msg,
            Toast.LENGTH_LONG
        )
        toast.setGravity(Gravity.TOP, 0, 0)
        toast.show()
    }


    fun randomizeColor(colorsList:List<Color>): Color {
        val randomNumber = Random.nextInt(colorsList.size)
        return colorsList[randomNumber]
    }

    /**
    Get task cards with isDone = true and return ArrayList.
    The reason why this method created is because of Query that gets all task cards with isDone = true doesnt work,
    the reason is TaskCardWithScheduledDate class.
     */
    fun getAllDoneTaskCards(taskCardsList: List<TaskCard>): ArrayList<TaskCard> {
        val doneTasks = ArrayList<TaskCard>()
        taskCardsList.forEach { taskCard ->
            if (taskCard.isDone) {
                doneTasks.add(taskCard)
            }
        }
        return doneTasks
    }

    fun getAllNotDoneTaskCards(taskCardsList: List<TaskCard>): ArrayList<TaskCard> {
        val notDoneTasks = ArrayList<TaskCard>()
        taskCardsList.forEach { taskCard ->
            if (!taskCard.isDone) {
                notDoneTasks.add(taskCard)
            }
        }
        return notDoneTasks
    }
}