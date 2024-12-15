package com.aljazkajtna.kmpshowcase.ui.postcreate.di

import com.aljazkajtna.kmpshowcase.ui.postcreate.PostCreateViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val postCreateModule = module {
    viewModel {
        PostCreateViewModel(
            usersRepository = get()
        )
    }
}
