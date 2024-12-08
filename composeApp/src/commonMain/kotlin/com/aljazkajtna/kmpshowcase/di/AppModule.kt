package com.aljazkajtna.kmpshowcase.di

import com.aljazkajtna.kmpshowcase.users.ui.UserListViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.includes
import org.koin.dsl.koinConfiguration
import org.koin.dsl.module

expect fun nativeConfig() : KoinAppDeclaration

val koinConfig = koinConfiguration {
    includes(nativeConfig())
    modules(appModule)
}

val appModule = module {
//    singleOf(::UserRepositoryImpl) { bind<UserRepository>() }
//    viewModelOf(::Userlist)
    viewModelOf(::UserListViewModel)
}
