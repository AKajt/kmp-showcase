package com.aljazkajtna.kmpshowcase.ui.userposts.di

import com.aljazkajtna.kmpshowcase.ui.userposts.UserPostsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val userPostsModule = module {
    viewModel {
        UserPostsViewModel(
            usersRepository = get()
        )
    }
}
