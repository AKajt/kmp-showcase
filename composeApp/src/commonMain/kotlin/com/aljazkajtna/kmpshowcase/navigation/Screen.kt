package com.aljazkajtna.kmpshowcase.navigation

import kmp_showcase.composeapp.generated.resources.Res
import kmp_showcase.composeapp.generated.resources.screen_user_details_create
import kmp_showcase.composeapp.generated.resources.screen_user_details_edit
import kmp_showcase.composeapp.generated.resources.screen_user_posts
import kmp_showcase.composeapp.generated.resources.screen_users
import kmp_showcase.composeapp.generated.resources.screen_users_stats
import org.jetbrains.compose.resources.StringResource

enum class Screen(
    val title: StringResource,
    val route: String
) {
    UserList(
        title = Res.string.screen_users,
        route = "users"
    ),
    UserCreate(
        title = Res.string.screen_user_details_create,
        route = "user_create"
    ),
    UserEdit(
        title = Res.string.screen_user_details_edit,
        route = "user_edit"
    ),
    UsersStats(
        title = Res.string.screen_users_stats,
        route = "users_stats"
    ),
    UserPosts(
        title = Res.string.screen_user_posts,
        route = "users_posts"
    )
}
