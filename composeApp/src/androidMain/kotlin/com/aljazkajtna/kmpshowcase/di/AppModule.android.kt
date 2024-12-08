package com.aljazkajtna.kmpshowcase.di

import com.aljazkajtna.kmpshowcase.MainApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.dsl.koinConfiguration

actual fun nativeConfig() = koinConfiguration {
    androidLogger()
    androidContext(MainApplication.instance ?: error("No Android application context set"))
}
