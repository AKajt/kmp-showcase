package com.aljazkajtna.kmpshowcase.ui.userposts

import com.aljazkajtna.kmpshowcase.ui.model.UserPostUiModel

sealed class UserPostsScreenState {
    data object Idle : UserPostsScreenState()
    data class Ready(
        val posts: List<UserPostUiModel> = emptyList(),
    ) : UserPostsScreenState()
    data object Failed : UserPostsScreenState()
    data object Success : UserPostsScreenState()
}
