package com.aljazkajtna.kmpshowcase.ui.userlist

import com.aljazkajtna.kmpshowcase.ui.model.UserExternalUiModel
import com.aljazkajtna.kmpshowcase.ui.model.UserLocalUiModel

data class UserListScreenState(
    val localUsers: List<UserLocalUiModel> = emptyList(),
    val externalUsers: List<UserExternalUiModel> = emptyList()
)
