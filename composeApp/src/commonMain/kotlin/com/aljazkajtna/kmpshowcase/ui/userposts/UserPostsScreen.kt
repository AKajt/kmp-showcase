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
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.aljazkajtna.kmpshowcase.ComposableLifecycle
import com.aljazkajtna.kmpshowcase.navigation.Screen
import com.aljazkajtna.kmpshowcase.ui.model.UserPostUiModel
import com.aljazkajtna.kmpshowcase.ui.userposts.UserPostsScreenState
import com.aljazkajtna.kmpshowcase.ui.userposts.UserPostsViewModel
import kmp_showcase.composeapp.generated.resources.Res
import kmp_showcase.composeapp.generated.resources.screen_user_posts
import kmp_showcase.composeapp.generated.resources.screen_users_loading
import kmp_showcase.composeapp.generated.resources.screen_users_show_stats
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun UserPostsScreen(
    userId: Int,
    navController: NavController
) {
    val viewModel = koinViewModel<UserPostsViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    ComposableLifecycle { source, event ->
        if (event == Lifecycle.Event.ON_START) {
            viewModel.setup(userId)
        }
        if (event == Lifecycle.Event.ON_RESUME) {
            viewModel.loadUserPosts()
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
                        Text(text = stringResource(Res.string.screen_user_posts))
                    }
                },
                modifier = Modifier.statusBarsPadding()
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.PostCreate.route + "/${userId}")
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null
                )
            }
        }
    ) { paddingValues ->
        when (uiState) {
            is UserPostsScreenState.Ready -> {
                val posts = (uiState as UserPostsScreenState.Ready).posts
                if (posts.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        items(posts, key = { it.id }) { post ->
                            UserPostCard(post) {
                                // no click action needed currently
                            }
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
                            text = stringResource(Res.string.screen_users_loading)
                        )
                    }
                }
            }

            UserPostsScreenState.Failed,
            UserPostsScreenState.Idle,
            UserPostsScreenState.Success -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        textAlign = TextAlign.Center,
                        text = stringResource(Res.string.screen_users_loading)
                    )
                }
            }
        }

        LaunchedEffect(uiState) {
            when (uiState) {
                UserPostsScreenState.Failed -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = "Fetching posts for user failed",
                        duration = SnackbarDuration.Short
                    )
                    navController.popBackStack()
                }

                UserPostsScreenState.Idle,
                is UserPostsScreenState.Ready,
                UserPostsScreenState.Success -> {
                    // do nothing
                }
            }
        }
    }
}

@Composable
fun UserPostCard(
    post: UserPostUiModel,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = 2.dp,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = post.title,
                style = MaterialTheme.typography.h6
            )
            Text(
                text = post.body,
                style = MaterialTheme.typography.body2
            )
        }
    }
}
