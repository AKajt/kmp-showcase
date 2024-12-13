package com.aljazkajtna.kmpshowcase.ui.userlist

import com.aljazkajtna.kmpshowcase.ui.model.UserUiModel

data class UserListScreenState(
    val users: List<UserUiModel> = emptyList(),
)
