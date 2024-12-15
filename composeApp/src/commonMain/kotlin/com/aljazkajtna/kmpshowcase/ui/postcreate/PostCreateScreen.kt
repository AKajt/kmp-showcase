package com.aljazkajtna.kmpshowcase.ui.postcreate

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kmp_showcase.composeapp.generated.resources.Res
import kmp_showcase.composeapp.generated.resources.screen_create_post
import kmp_showcase.composeapp.generated.resources.screen_post_create_body
import kmp_showcase.composeapp.generated.resources.screen_post_create_title
import kmp_showcase.composeapp.generated.resources.screen_user_details_button_create
import org.jetbrains.compose.resources.stringResource

@Composable
fun PostCreateScreen(
    userId: Int,
    navController: NavController
) {
    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(Res.string.screen_create_post))
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
                    .height(200.dp) // Adjust height as needed
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                // TODO: Handle post creation logic
            }) {
                Text(stringResource(Res.string.screen_user_details_button_create))
            }
        }
    }
}
