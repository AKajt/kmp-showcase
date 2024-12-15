package com.aljazkajtna.kmpshowcase.ui.userlist

import com.aljazkajtna.kmpshowcase.ui.model.UserExternalUiModel
import com.aljazkajtna.kmpshowcase.ui.model.UserLocalUiModel

sealed class UserListScreenState(
    open val localUsers: List<UserLocalUiModel> = emptyList(),
) {
    data class Idle(
        override val localUsers: List<UserLocalUiModel> = emptyList()
    ) : UserListScreenState()
    data class Failed(
        override val localUsers: List<UserLocalUiModel> = emptyList()
    ) : UserListScreenState()
    data class Success(
        override val localUsers: List<UserLocalUiModel> = emptyList(),
        val externalUsers: List<UserExternalUiModel> = emptyList()
    ) : UserListScreenState()
}
