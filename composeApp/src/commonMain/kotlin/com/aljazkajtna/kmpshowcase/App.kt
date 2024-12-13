package com.aljazkajtna.kmpshowcase

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aljazkajtna.kmpshowcase.di.koinConfig
import com.aljazkajtna.kmpshowcase.navigation.Screen
import com.aljazkajtna.kmpshowcase.ui.usercreate.UserCreateScreen
import com.aljazkajtna.kmpshowcase.ui.userlist.UserListScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

@Composable
@Preview
fun App() {
    KoinApplication(
        application = koinConfig
    ) {
        val navController = rememberNavController()
        MaterialTheme {
            NavHost(
                navController = navController,
                startDestination = Screen.UserList.name
            ) {
                composable(route = Screen.UserList.name) {
                    UserListScreen(
                        navController = navController
                    )
                }
                composable(route = Screen.CreateUser.name) {
                    UserCreateScreen(
                        navController = navController
                    )
                }
            }
        }
    }
}
