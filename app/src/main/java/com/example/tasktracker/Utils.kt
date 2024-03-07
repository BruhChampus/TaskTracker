package com.example.tasktracker

import android.content.Context
import android.view.Gravity
import android.widget.Toast
import androidx.compose.ui.graphics.Color
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

}