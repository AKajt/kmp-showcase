package com.aljazkajtna.kmpshowcase.ui.userdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.aljazkajtna.kmpshowcase.ComposableLifecycle
import com.aljazkajtna.kmpshowcase.domain.model.Gender
import kmp_showcase.composeapp.generated.resources.Res
import kmp_showcase.composeapp.generated.resources.screen_user_details_create
import kmp_showcase.composeapp.generated.resources.screen_user_details_edit
import kmp_showcase.composeapp.generated.resources.screen_user_details_field_age
import kmp_showcase.composeapp.generated.resources.screen_user_details_field_first_name
import kmp_showcase.composeapp.generated.resources.screen_user_details_field_gender
import kmp_showcase.composeapp.generated.resources.screen_user_details_field_last_name
import kmp_showcase.composeapp.generated.resources.screen_user_details_loading
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

enum class UserDetailsScreenMode {
    Create,
    Edit
}

@Composable
fun UserDetailsScreen(
    mode: UserDetailsScreenMode,
    userId: String? = null,
    navController: NavController
) {
    val viewModel = koinViewModel<UserDetailsViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    ComposableLifecycle { source, event ->
        if (event == Lifecycle.Event.ON_START) {
            viewModel.onStart(userId)
        }
    }

    val titleRes = remember {
        if (mode == UserDetailsScreenMode.Create) {
            Res.string.screen_user_details_create
        } else {
            Res.string.screen_user_details_edit
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(titleRes)
                    )
                },
                modifier = Modifier.statusBarsPadding()
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (uiState) {
                is UserDetailsScreenState.Loading -> {
                    RenderLoadingState()
                }

                is UserDetailsScreenState.Ready -> {
                    RenderReadyState(
                        mode = mode,
                        viewModel = viewModel,
                        navController = navController,
                        state = uiState as UserDetailsScreenState.Ready
                    )
                }
            }
        }
    }
}

@Composable
fun RenderLoadingState() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            textAlign = TextAlign.Center,
            text = stringResource(Res.string.screen_user_details_loading)
        )
    }
}

@Composable
fun RenderReadyState(
    mode: UserDetailsScreenMode,
    viewModel: UserDetailsViewModel,
    navController: NavController,
    state: UserDetailsScreenState.Ready
) {
    var firstName by remember { mutableStateOf(state.firstName) }
    var lastName by remember { mutableStateOf(state.lastName) }
    var age by remember { mutableStateOf(state.age) }
    var selectedGender by remember { mutableStateOf(state.gender) }
    var expanded by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = firstName,
        onValueChange = { firstName = it },
        label = {
            Text(
                text = stringResource(Res.string.screen_user_details_field_first_name)
            )
        },
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    )
    OutlinedTextField(
        value = lastName,
        onValueChange = { lastName = it },
        label = {
            Text(
                text = stringResource(Res.string.screen_user_details_field_last_name)
            )
        },
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    )
    OutlinedTextField(
        value = age,
        onValueChange = { age = it },
        label = {
            Text(
                text = stringResource(Res.string.screen_user_details_field_age)
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    )

    // Gender Dropdown
    Box(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        OutlinedTextField(
            value = selectedGender.name,
            onValueChange = { },
            label = {
                Text(
                    text = stringResource(Res.string.screen_user_details_field_gender)
                )
            },
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = null
                    )
                }
            },
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            Gender.entries.forEach { gender ->
                DropdownMenuItem(onClick = {
                    selectedGender = gender
                    expanded = false
                }) {
                    Text(text = gender.name)
                }
            }
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    when(mode) {
        UserDetailsScreenMode.Create -> {
            Button(onClick = {
                viewModel.createUser(
                    firstName,
                    lastName,
                    age.toIntOrNull() ?: 0,
                    selectedGender
                )
                navController.popBackStack()
            }) {
                Text(
                    text = stringResource(Res.string.screen_user_details_create)
                )
            }
        }
        UserDetailsScreenMode.Edit -> {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = {
                    viewModel.onUpdateUserClick(
                        state.id,
                        firstName,
                        lastName,
                        age,
                        selectedGender
                    )
                    navController.popBackStack()
                }) {
                    Text("Update")
                }
                Button(
                    onClick = {
                        viewModel.onDeleteUser(state.id)
                        navController.popBackStack()
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                ) {
                    Text("Delete")
                }
            }
        }
    }
}
