package com.aljazkajtna.kmpshowcase.ui.usercreate

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aljazkajtna.kmpshowcase.domain.model.Gender
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun UserCreateScreen(
    navController: NavController
) {
    val viewModel = koinViewModel<UserCreateViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var selectedGender by remember { mutableStateOf(Gender.MALE) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Create User"
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
            OutlinedTextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text("First Name") },
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            )
            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Last Name") },
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            )
            OutlinedTextField(
                value = age,
                onValueChange = { age = it },
                label = { Text("Age") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            )

            // Gender Dropdown
            Box(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                OutlinedTextField(
                    value = selectedGender.name, // Display selected gender name
                    onValueChange = { }, // Read-only
                    label = {
                        Text(
                            text = "Gender"
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = { expanded = !expanded }) {
                            Icon(
                                imageVector = Icons.Filled.KeyboardArrowDown,
                                contentDescription = "Dropdown"
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

            Button(onClick = {
                // TODO: Call viewModel to create user with the entered data
                // viewModel.createUser(firstName, lastName, age.toIntOrNull() ?: 0, selectedGender)
                navController.popBackStack() // Navigate back after creating
            }) {
                Text(
                    text = "Create User"
                )
            }
        }
    }
}
