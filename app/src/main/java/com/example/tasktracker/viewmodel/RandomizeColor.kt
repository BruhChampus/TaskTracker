package com.example.tasktracker.viewmodel

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

fun randomizeColor(colorsList:List<Color>):Color{
    val randomNumber = Random.nextInt(colorsList.size)
    return colorsList[randomNumber]
}