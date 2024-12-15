package com.aljazkajtna.kmpshowcase

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aljazkajtna.kmpshowcase.di.koinConfig
import com.aljazkajtna.kmpshowcase.navigation.Screen
import com.aljazkajtna.kmpshowcase.ui.userdetails.UserDetailsScreen
import com.aljazkajtna.kmpshowcase.ui.userdetails.UserDetailsScreenMode
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
                startDestination = Screen.UserList.route
            ) {
                composable(route = Screen.UserList.route) {
                    UserListScreen(
                        navController = navController
                    )
                }
                composable(route = Screen.UserCreate.route) {
                    UserDetailsScreen(
                        mode = UserDetailsScreenMode.Create,
                        navController = navController
                    )
                }
                composable(
                    route = Screen.UserEdit.route + "/{userId}",
                    arguments = listOf(navArgument("userId") { type = NavType.StringType })
                ) { arguments ->
                    val userId = arguments.arguments?.getString("userId")
                    UserDetailsScreen(
                        mode = UserDetailsScreenMode.Edit,
                        userId = userId,
                        navController = navController
                    )
                }
                composable(route = Screen.UsersStats.route) {

                }
            }
        }
    }
}

@Composable
fun ComposableLifecycle(
    lifeCycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onEvent: (LifecycleOwner, Lifecycle.Event) -> Unit
) {
    DisposableEffect(lifeCycleOwner) {
        val observer = LifecycleEventObserver { source, event ->
            onEvent(source, event)
        }
        lifeCycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifeCycleOwner.lifecycle.removeObserver(observer)
        }
    }
}
