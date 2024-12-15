package com.aljazkajtna.kmpshowcase.ui.userposts

import com.aljazkajtna.kmpshowcase.ui.model.UserPostUiModel

data class UserPostsScreenState(
    val posts: List<UserPostUiModel> = emptyList(),
)
