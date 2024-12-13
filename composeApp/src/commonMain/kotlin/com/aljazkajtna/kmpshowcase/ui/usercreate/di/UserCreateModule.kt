package com.aljazkajtna.kmpshowcase.ui.usercreate.di

import com.aljazkajtna.kmpshowcase.ui.usercreate.UserCreateViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val userCreateModule = module {
    viewModel {
        UserCreateViewModel(
            usersRepository = get(),
            nativeUtils = get()
        )
    }
}
