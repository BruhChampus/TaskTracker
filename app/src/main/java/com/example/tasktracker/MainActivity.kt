package com.example.tasktracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tasktracker.view.bottomnavigat.BottomMenuView
import com.example.tasktracker.view.screens.CreateScreen
import com.example.tasktracker.view.screens.DoneScreen
import com.example.tasktracker.view.screens.HomeScreen
import com.example.tasktracker.ui.theme.TaskTrackerTheme
import com.example.tasktracker.view.bottomnavigat.BottomNavBarItem
import com.example.tasktracker.view.bottomnavigat.itemsList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskTrackerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TaskTrackerMainScreen()
                }
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun TaskTrackerPreview() {
    TaskTrackerTheme {
        TaskTrackerMainScreen()
    }
}

@Composable
fun TaskTrackerMainScreen(){
    val navController = rememberNavController()
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.weight(0.85f)) {
            BottomNavGraph(navController)
        }
        Box(modifier = Modifier.weight(0.15f)) {
            BottomMenuView(
                items = itemsList,
                modifier = Modifier.align(Alignment.BottomCenter),
                navController = navController
            )
        }
    }
}

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavBarItem.Home.route) {
        composable(BottomNavBarItem.Home.route) { HomeScreen() }
        composable(BottomNavBarItem.Create.route) { CreateScreen()}
        composable(BottomNavBarItem.Done.route) { DoneScreen() }
    }
}







/*

SECOND VARIATION OF BOTTOMBAR

@Composable
fun BottomNavigationBar() {
    val navController = rememberNavController()
    Scaffold(bottomBar = { BottomBar(navController = navController) }) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues))
        BottomNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val selectedItemIndex = rememberSaveable {
        mutableStateOf(0)
    }
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
    ) {
        itemsList.forEachIndexed { index, item ->
            if (selectedItemIndex.value == index) {
                Spacer(
                    Modifier
                        .background(MaterialTheme.colorScheme.secondary)
                        .size(width = 14.dp, height = 4.dp)
                        .align(Alignment.Bottom)
                )
            }
            NavigationBarItem(
                modifier = Modifier.padding(15.dp),
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = MaterialTheme.colorScheme.secondary,
                    unselectedIconColor = MaterialTheme.colorScheme.secondary
                ),
                selected = selectedItemIndex.value == index,
                onClick = {
                    selectedItemIndex.value = index
                    navController.navigate(item.route)
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = "",
                        modifier = Modifier.size(size = 75.dp),
                    )

                })
        }

    }

}

 */