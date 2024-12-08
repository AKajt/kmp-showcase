package com.aljazkajtna.kmpshowcase.di

import org.koin.dsl.koinConfiguration

actual fun nativeConfig() = koinConfiguration {
    printLogger()
}
