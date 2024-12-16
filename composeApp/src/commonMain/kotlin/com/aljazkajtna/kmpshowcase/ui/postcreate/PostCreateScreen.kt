package com.aljazkajtna.kmpshowcase.ui.postcreate

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.aljazkajtna.kmpshowcase.ComposableLifecycle
import kmp_showcase.composeapp.generated.resources.Res
import kmp_showcase.composeapp.generated.resources.screen_create_post
import kmp_showcase.composeapp.generated.resources.screen_post_create_body
import kmp_showcase.composeapp.generated.resources.screen_post_create_title
import kmp_showcase.composeapp.generated.resources.screen_user_details_button_create
import kmp_showcase.composeapp.generated.resources.screen_users_show_stats
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PostCreateScreen(
    userId: Int,
    navController: NavController
) {
    val viewModel = koinViewModel<PostCreateViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }

    ComposableLifecycle { source, event ->
        if (event == Lifecycle.Event.ON_START) {
            viewModel.setup(userId)
        }
    }

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = { navController.popBackStack() }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(Res.string.screen_users_show_stats)
                            )
                        }
                        Text(stringResource(Res.string.screen_create_post))
                    }
                },
                modifier = Modifier.statusBarsPadding()
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(stringResource(Res.string.screen_post_create_title)) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = body,
                onValueChange = { body = it },
                label = { Text(stringResource(Res.string.screen_post_create_body)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                viewModel.createPost(
                    title = title,
                    body = body
                )
            }) {
                Text(stringResource(Res.string.screen_user_details_button_create))
            }
        }

        LaunchedEffect(uiState) {
            when (uiState) {
                PostCreateScreenState.Success -> {
                    navController.popBackStack()
                }

                PostCreateScreenState.Failed -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = "Post creation failed",
                        duration = SnackbarDuration.Short
                    )
                    navController.popBackStack()
                }

                PostCreateScreenState.Idle -> {
                    // do nothing
                }
            }
        }
    }
}
