package com.example.tasktracker.domain

import java.text.SimpleDateFormat
import java.util.Locale

object MyTimeConverter {
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

    //Millis to something like 24 july 2024
    fun transformMillisToDate(timeInMillis:Long): String {
        val dateFormatter =
            SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        return dateFormatter.format(timeInMillis)
    }
}