package com.example.tasktracker.view.bottomnavigat

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@Composable
fun BottomMenuView(
    items: List<BottomNavBarItem>,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    activeHighlightColor: Color = MaterialTheme.colorScheme.secondary,
    activeIconColor: Color = MaterialTheme.colorScheme.primary,
    activeHighlightBackgroundShape: Shape = CircleShape,
    inactiveIconColor: Color = MaterialTheme.colorScheme.secondary,
    initialSelectedItemIndex: Int = 1,
    navController:NavHostController
) {
    val selectedItemIndex = remember {
        mutableStateOf(initialSelectedItemIndex)
    }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(containerColor)
    ) {
        items.forEachIndexed { index, item ->
            BottomMenuItem(
                item = item, isSelected = index == selectedItemIndex.value,
                activeHighlightColor = activeHighlightColor,
                activeHighlightBackgroundShape = activeHighlightBackgroundShape,
                activeIconColor = activeIconColor,
                inactiveIconColor = inactiveIconColor,
            ) {
                selectedItemIndex.value = index
                navController.navigate(item.route)

            }
        }
    }
}

@Composable
fun BottomMenuItem(
    item: BottomNavBarItem,
    isSelected: Boolean = false,
    activeHighlightColor: Color = Color.Green,
    activeHighlightBackgroundShape: Shape = RectangleShape,
    activeIconColor: Color = Color.White,
    inactiveIconColor: Color = Color.Gray,
    onItemClick: () -> Unit
) {

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(15.dp)
            .clickable{
                onItemClick()
            }) {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(activeHighlightBackgroundShape)
                    .background(if (isSelected) activeHighlightColor else Color.Transparent)
                    .padding(5.dp)
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = "",
                    tint = if (isSelected) activeIconColor else inactiveIconColor,
                    modifier = Modifier
                        .size(65.dp)
                        .padding(5.dp)
                )

            }
            Spacer(
                modifier = Modifier.padding(top = 10.dp)
                    .height(4.dp).width(65.dp)
                    .align(Alignment.CenterHorizontally)
                    .background(if (isSelected) MaterialTheme.colorScheme.secondary else Color.Transparent)
            )


    }

}