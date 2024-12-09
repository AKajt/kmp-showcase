package com.aljazkajtna.kmpshowcase.navigation

import kmp_showcase.composeapp.generated.resources.Res
import kmp_showcase.composeapp.generated.resources.screen_user_list
import org.jetbrains.compose.resources.StringResource

enum class Screen(val title: StringResource) {
    UserList(title = Res.string.screen_user_list),
}
