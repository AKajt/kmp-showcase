package com.aljazkajtna.kmpshowcase.ui.userlist.di

import com.aljazkajtna.kmpshowcase.ui.userlist.UserListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val userListModule = module {
    viewModel {
        UserListViewModel(
            usersRepository = get()
        )
    }
}
