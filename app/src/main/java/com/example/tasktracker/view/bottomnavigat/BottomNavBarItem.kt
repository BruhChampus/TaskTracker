package com.example.tasktracker.view.bottomnavigat

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavBarItem(val route: String, val icon: ImageVector, val label: String) {
    object Create : BottomNavBarItem("Create", Icons.Default.DateRange, "Create")
    object Home : BottomNavBarItem("Home", Icons.Default.Home, "Home")
    object Done : BottomNavBarItem("Done", Icons.Default.Done, "Done")

}
val itemsList = listOf(
    BottomNavBarItem.Create,
    BottomNavBarItem.Home,
    BottomNavBarItem.Done,
)
