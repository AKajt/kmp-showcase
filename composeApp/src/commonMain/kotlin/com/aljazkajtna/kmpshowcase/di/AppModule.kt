package com.aljazkajtna.kmpshowcase.di

import com.aljazkajtna.kmpshowcase.data.LocalSource
import com.aljazkajtna.kmpshowcase.data.UsersDataRepository
import com.aljazkajtna.kmpshowcase.data.local.LocalDataSource
import com.aljazkajtna.kmpshowcase.data.local.cache.Database
import com.aljazkajtna.kmpshowcase.domain.model.UsersRepository
import com.aljazkajtna.kmpshowcase.ui.UserListViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.includes
import org.koin.dsl.koinConfiguration
import org.koin.dsl.module

expect fun nativeConfig(): KoinAppDeclaration


val koinConfig = koinConfiguration {
    includes(nativeConfig())
    modules(appModule)
}

val appModule = module {
    single {
        Database(
            databaseDriverFactory = get()
        )
    }
    single<LocalSource> {
        LocalDataSource(
            database = get()
        )
    }
    single<UsersRepository> {
        UsersDataRepository(
            localSource = get()
        )
    }

    viewModel {
        UserListViewModel(
            usersRepository = get()
        )
    }
}
