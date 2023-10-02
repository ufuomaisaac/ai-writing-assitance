package com.example.aiwritingassitance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aiwritingassitance.screens.AccountDestination
import com.example.aiwritingassitance.screens.AccountScreen
import com.example.aiwritingassitance.screens.ChatDestination
import com.example.aiwritingassitance.screens.ChatScreen
import com.example.aiwritingassitance.screens.HomeDestination
import com.example.aiwritingassitance.screens.HomeScreen
import com.example.aiwritingassitance.screens.allDestinations
import com.example.aiwritingassitance.ui.theme.AIWritingAssitanceTheme


@OptIn(ExperimentalMaterial3Api::class)
class BottomNavigationActivity : ComponentActivity() {

    private var selectedItem by mutableStateOf(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            AIWritingAssitanceTheme {
                var navController = rememberNavController()

                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            allDestinations.forEachIndexed { index, item ->
                                NavigationBarItem(
                                    icon = {
                                        Icon(
                                            imageVector = item.icon,
                                            contentDescription = item.route
                                        )
                                    },
                                    label = { Text(text = item.label) },
                                    selected = selectedItem == index,
                                    onClick = {
                                        selectedItem = index
                                        navController.navigate(item.route) {
//                                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
//                                            launchSingleTop = true
//                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    }

                ) {
                    NavHost(
                        navController = navController,
                        startDestination = HomeDestination.route,
                        modifier = Modifier.padding(it)
                    ) {
                        composable(HomeDestination.route) {
                            HomeScreen(
                                navController = navController,
                                onNavigateToReward = {
                                    selectedItem = 1
                                }
                            )
                        }
                        composable(ChatDestination.route) { ChatScreen() }
                        composable(AccountDestination.route) { AccountScreen() }
                    }
                }
            }
        }
    }
}

