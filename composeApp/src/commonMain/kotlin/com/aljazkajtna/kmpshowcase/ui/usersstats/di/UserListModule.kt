package com.aljazkajtna.kmpshowcase.ui.usersstats.di

import com.aljazkajtna.kmpshowcase.ui.usersstats.UsersStatsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val usersStatsModule = module {
    viewModel {
        UsersStatsViewModel(
            usersRepository = get()
        )
    }
}
