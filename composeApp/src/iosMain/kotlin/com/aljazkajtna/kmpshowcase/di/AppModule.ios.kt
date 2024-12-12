package com.aljazkajtna.kmpshowcase.di

import com.aljazkajtna.kmpshowcase.data.local.cache.DatabaseDriverFactory
import com.aljazkajtna.kmpshowcase.data.local.cache.IOSDatabaseDriverFactory
import org.koin.dsl.koinConfiguration
import org.koin.dsl.module

actual fun nativeConfig() = koinConfiguration {
    printLogger()
    modules(
        module {
            single<DatabaseDriverFactory> { IOSDatabaseDriverFactory() }
        }
    )
}
