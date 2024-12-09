package com.aljazkajtna.kmpshowcase.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.aljazkajtna.kmpshowcase.Greeting
import kmp_showcase.composeapp.generated.resources.Res
import kmp_showcase.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource

@Composable
fun UserListScreen(
    viewModel: UserListViewModel
) {
    val state = viewModel.uiState.collectAsState()

    var showContent by remember { mutableStateOf(false) }

    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        if (state.value.users != null) {
            Button(onClick = { showContent = !showContent }) {
                Text("Trolololo!")
            }
        } else {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
        }
        AnimatedVisibility(showContent) {
            val greeting = remember { Greeting().greet() }
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painterResource(Res.drawable.compose_multiplatform), null)
                Text("Compose: $greeting")
            }
        }
    }
//    Column(
//        modifier = Modifier.fillMaxSize(),
//    ) {  }
    // TODO:
}
