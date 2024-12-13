package com.aljazkajtna.kmpshowcase.di

import com.aljazkajtna.kmpshowcase.MainApplication
import com.aljazkajtna.kmpshowcase.core.NativeUtils
import com.aljazkajtna.kmpshowcase.data.local.cache.AndroidDatabaseDriverFactory
import com.aljazkajtna.kmpshowcase.data.local.cache.DatabaseDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.dsl.koinConfiguration
import org.koin.dsl.module

actual fun nativeConfig() = koinConfiguration {
    androidLogger()
    androidContext(MainApplication.instance ?: error("No Android application context set"))
    modules(
        module {
            single<DatabaseDriverFactory> { AndroidDatabaseDriverFactory(get()) }
            single { NativeUtils() }
        }
    )
}
