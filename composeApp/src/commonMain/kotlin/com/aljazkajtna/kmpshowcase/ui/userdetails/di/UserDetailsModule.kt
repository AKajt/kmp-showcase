package com.aljazkajtna.kmpshowcase.ui.userdetails.di

import com.aljazkajtna.kmpshowcase.ui.userdetails.UserDetailsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val userDetailsModule = module {
    viewModel {
        UserDetailsViewModel(
            usersRepository = get(),
            nativeUtils = get()
        )
    }
}
