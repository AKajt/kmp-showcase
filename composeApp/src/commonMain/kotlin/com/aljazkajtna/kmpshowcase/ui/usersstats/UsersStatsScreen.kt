package com.aljazkajtna.kmpshowcase.ui.usersstats

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.aljazkajtna.kmpshowcase.ComposableLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun UsersStatsScreen(
    navController: NavController
) {
    val viewModel = koinViewModel<UsersStatsViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    ComposableLifecycle { source, event ->
        if (event == Lifecycle.Event.ON_START) {
            viewModel.loadStats()
        }
    }

}
