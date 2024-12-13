package com.aljazkajtna.kmpshowcase.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.aljazkajtna.kmpshowcase.navigation.Screen
import com.aljazkajtna.kmpshowcase.ui.model.UserUiModel
import kmp_showcase.composeapp.generated.resources.Res
import kmp_showcase.composeapp.generated.resources.screen_user_age
import kmp_showcase.composeapp.generated.resources.screen_user_gender
import kmp_showcase.composeapp.generated.resources.screen_user_loading
import kmp_showcase.composeapp.generated.resources.screen_user_name
import kmp_showcase.composeapp.generated.resources.screen_user_show_stats
import kmp_showcase.composeapp.generated.resources.screen_user_title
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun UserListScreen() {
    val viewModel = koinViewModel<UserListViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(Res.string.screen_user_title)) },
                actions = {
                    IconButton(onClick = viewModel::onShowStatsClick) {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = stringResource(Res.string.screen_user_show_stats)
                        )
                    }
                },
                modifier = Modifier.statusBarsPadding()
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.CreateUser.name) }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null
                )
            }
        }
    ) { paddingValues ->
        val users = uiState.users
        if (users.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(users) { user ->
                    UserCard(user)
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center,
                    text = stringResource(Res.string.screen_user_loading)
                )
            }
        }
    }
}

@Composable
fun UserCard(user: UserUiModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(
                        Res.string.screen_user_name,
                        user.firstName,
                        user.lastName
                    ),
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = stringResource(Res.string.screen_user_age, user.age),
                    style = MaterialTheme.typography.body2
                )
                Text(
                    text = stringResource(Res.string.screen_user_gender, user.gender.name),
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}
