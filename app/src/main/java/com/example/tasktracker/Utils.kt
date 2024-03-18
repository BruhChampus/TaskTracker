package com.example.tasktracker

import android.content.Context
import android.view.Gravity
import android.widget.Toast
import androidx.compose.ui.graphics.Color
import com.example.tasktracker.data.model.TaskCard
import kotlin.random.Random

object Utils{

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