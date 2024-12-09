package com.aljazkajtna.kmpshowcase.ui

import com.aljazkajtna.kmpshowcase.ui.model.UserUiModel

data class UserListScreenState(
    val users: List<UserUiModel> = emptyList(),
)
