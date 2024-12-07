package com.aljazkajtna.kmpshowcase.users.ui

import com.aljazkajtna.kmpshowcase.users.ui.model.UserUiModel

data class UserListScreenState(
    val users: List<UserUiModel> = emptyList(),
)
