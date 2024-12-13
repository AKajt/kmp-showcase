package com.aljazkajtna.kmpshowcase.navigation

import kmp_showcase.composeapp.generated.resources.Res
import kmp_showcase.composeapp.generated.resources.screen_user_create
import kmp_showcase.composeapp.generated.resources.screen_users
import org.jetbrains.compose.resources.StringResource

enum class Screen(val title: StringResource) {
    UserList(title = Res.string.screen_users),
    CreateUser(title = Res.string.screen_user_create),
}
