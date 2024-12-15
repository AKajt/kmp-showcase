package com.aljazkajtna.kmpshowcase.ui.model

import com.aljazkajtna.kmpshowcase.domain.external.UserPostDomainModel

data class UserPostUiModel(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)

fun UserPostDomainModel.toUi() = UserPostUiModel(
    userId = userId,
    id = id,
    title = title,
    body = body
)
