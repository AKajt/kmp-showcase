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
import com.aljazkajtna.kmpshowcase.ui.model.UserUiModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun UserListScreen() {
    val viewModel = koinViewModel<UserListViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    val users = uiState.users

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("User List") },
                actions = {
                    IconButton(onClick = viewModel::onShowStatsClick) {
                        Icon(Icons.Filled.Info, contentDescription = "Show Stats")
                    }
                },
                modifier = Modifier.statusBarsPadding()
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = viewModel::onCreateUserClick) {
                Icon(Icons.Filled.Add, contentDescription = "Create User")
            }
        }
    ) { paddingValues ->
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
                    text = "Loading users..."
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
                    text = "${user.firstName} ${user.lastName}",
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = "Age: ${user.age}",
                    style = MaterialTheme.typography.body2
                )
                Text(
                    text = "Gender: ${user.gender.name}",
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}
