package com.aljazkajtna.kmpshowcase.ui.userlist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.rememberDismissState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.aljazkajtna.kmpshowcase.ComposableLifecycle
import com.aljazkajtna.kmpshowcase.navigation.Screen
import com.aljazkajtna.kmpshowcase.ui.model.UserExternalUiModel
import com.aljazkajtna.kmpshowcase.ui.model.UserLocalUiModel
import kmp_showcase.composeapp.generated.resources.Res
import kmp_showcase.composeapp.generated.resources.screen_users
import kmp_showcase.composeapp.generated.resources.screen_users_age
import kmp_showcase.composeapp.generated.resources.screen_users_email
import kmp_showcase.composeapp.generated.resources.screen_users_gender
import kmp_showcase.composeapp.generated.resources.screen_users_loading
import kmp_showcase.composeapp.generated.resources.screen_users_name
import kmp_showcase.composeapp.generated.resources.screen_users_phone
import kmp_showcase.composeapp.generated.resources.screen_users_show_stats
import kmp_showcase.composeapp.generated.resources.screen_users_tab_external
import kmp_showcase.composeapp.generated.resources.screen_users_tab_local
import kmp_showcase.composeapp.generated.resources.screen_users_website
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun UserListScreen(
    navController: NavController
) {
    val viewModel = koinViewModel<UserListViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    var selectedTabIndex by remember { mutableStateOf(0) }

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(Res.string.screen_users)) },
                actions = {
                    if (selectedTabIndex == 0) {
                        IconButton(
                            onClick = { navController.navigate(Screen.UsersStats.route) }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Info,
                                contentDescription = stringResource(Res.string.screen_users_show_stats)
                            )
                        }
                    }
                },
                modifier = Modifier.statusBarsPadding()
            )
        },
        floatingActionButton = {
            if (selectedTabIndex == 0) {
                FloatingActionButton(
                    onClick = { navController.navigate(Screen.UserCreate.route) }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = null
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            TabRow(selectedTabIndex = selectedTabIndex) {
                Tab(
                    selected = selectedTabIndex == 0,
                    onClick = { selectedTabIndex = 0 },
                    text = {
                        Text(text = stringResource(Res.string.screen_users_tab_local))
                    }
                )
                Tab(
                    selected = selectedTabIndex == 1,
                    onClick = { selectedTabIndex = 1 },
                    text = {
                        Text(text = stringResource(Res.string.screen_users_tab_external))
                    }
                )
            }

            when (selectedTabIndex) {
                0 -> {
                    RenderLocalTab(
                        navController = navController,
                        viewModel = viewModel,
                        uiState = uiState,
                    )
                }

                1 -> {
                    RenderExternalTab(
                        navController = navController,
                        viewModel = viewModel,
                        uiState = uiState,
                    )
                }
            }
        }

        LaunchedEffect(uiState) {
            when (uiState) {
                is UserListScreenState.Failed -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = "Fetching users failed",
                        duration = SnackbarDuration.Short
                    )
                }

                is UserListScreenState.Idle,
                is UserListScreenState.Success -> {
                    // do nothing
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun RenderLocalTab(
    navController: NavController,
    viewModel: UserListViewModel,
    uiState: UserListScreenState,
) {
    ComposableLifecycle { source, event ->
        if (event == Lifecycle.Event.ON_RESUME) {
            viewModel.loadLocalUsers()
        }
    }

    val users = uiState.localUsers
    if (users.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(users, key = { it.id }) { user ->
                val dismissState = rememberDismissState(
                    confirmStateChange = { dismissValue ->
                        if (dismissValue == DismissValue.DismissedToEnd
                            || dismissValue == DismissValue.DismissedToStart
                        ) {
                            viewModel.onDeleteUser(user.id)
                        }
                        true
                    }
                )

                SwipeToDismiss(
                    state = dismissState,
                    directions = setOf(
                        DismissDirection.StartToEnd,
                        DismissDirection.EndToStart
                    ),
                    dismissThresholds = { direction ->
                        FractionalThreshold(if (direction == DismissDirection.StartToEnd) 0.25f else 0.5f)
                    },
                    background = {
                        val color = when (dismissState.dismissDirection) {
                            DismissDirection.StartToEnd -> Color.Red
                            DismissDirection.EndToStart -> Color.Red
                            null -> Color.Transparent
                        }
                        Box(
                            Modifier
                                .fillMaxSize()
                                .padding(8.dp)
                                .background(
                                    color = color,
                                    shape = RoundedCornerShape(4.dp),
                                )
                        )
                    },
                    dismissContent = {
                        UserCard(
                            user,
                            onClick = {
                                navController.navigate(Screen.UserEdit.route + "/${user.id}")
                            }
                        )
                    }
                )
            }
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                textAlign = TextAlign.Center,
                text = stringResource(Res.string.screen_users_loading)
            )
        }
    }
}

@Composable
fun UserCard(
    user: UserLocalUiModel,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
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
                        Res.string.screen_users_name,
                        user.firstName,
                        user.lastName
                    ),
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = stringResource(Res.string.screen_users_age, user.age),
                    style = MaterialTheme.typography.body2
                )
                Text(
                    text = stringResource(Res.string.screen_users_gender, user.gender.name),
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Composable
private fun RenderExternalTab(
    navController: NavController,
    viewModel: UserListViewModel,
    uiState: UserListScreenState,
) {
    ComposableLifecycle { source, event ->
        if (event == Lifecycle.Event.ON_RESUME) {
            viewModel.loadExternalUsers()
        }
    }

    when (uiState) {
        is UserListScreenState.Success -> {
            if (uiState.externalUsers.isNotEmpty()) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(uiState.externalUsers, key = { it.id }) { user ->
                        ExternalUserCard(user) {
                            navController.navigate(Screen.UserPosts.route + "/${user.id}")
                        }
                    }
                }
            } else {
                EmptyStateMessage()
            }
        }

        else -> {
            EmptyStateMessage()
        }
    }
}

@Composable
fun EmptyStateMessage() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            textAlign = TextAlign.Center,
            text = stringResource(Res.string.screen_users_loading)
        )
    }
}

@Composable
fun ExternalUserCard(
    user: UserExternalUiModel,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
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
                    text = user.name,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = user.username,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = stringResource(Res.string.screen_users_email, user.email),
                    style = MaterialTheme.typography.body2
                )
                Text(
                    text = stringResource(Res.string.screen_users_phone, user.phone),
                    style = MaterialTheme.typography.body2
                )
                Text(
                    text = stringResource(Res.string.screen_users_website, user.website),
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}
