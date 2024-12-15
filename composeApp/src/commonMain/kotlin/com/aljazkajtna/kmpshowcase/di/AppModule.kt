package com.aljazkajtna.kmpshowcase.di

import com.aljazkajtna.kmpshowcase.data.local.LocalSource
import com.aljazkajtna.kmpshowcase.data.UsersDataRepository
import com.aljazkajtna.kmpshowcase.data.local.LocalDataSource
import com.aljazkajtna.kmpshowcase.data.local.cache.Database
import com.aljazkajtna.kmpshowcase.data.remote.RemoteDataSource
import com.aljazkajtna.kmpshowcase.data.remote.RemoteSource
import com.aljazkajtna.kmpshowcase.data.remote.network.JsonPlaceholderApi
import com.aljazkajtna.kmpshowcase.domain.UsersRepository
import com.aljazkajtna.kmpshowcase.ui.userdetails.di.userDetailsModule
import com.aljazkajtna.kmpshowcase.ui.userlist.di.userListModule
import com.aljazkajtna.kmpshowcase.ui.usersstats.di.usersStatsModule
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.internal.NamedCompanion
import kotlinx.serialization.json.Json
import org.koin.core.qualifier.named
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

    single {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        useAlternativeNames = false
                    }
                )
            }
        }
    }
    single(named("baseUrl")) {
        "https://jsonplaceholder.typicode.com/"
    }
    single {
        JsonPlaceholderApi(client = get())
    }
    single<RemoteSource> {
        RemoteDataSource(
            baseUrl = get(named("baseUrl")),
            httpClient = get()
        )
    }

    single<UsersRepository> {
        UsersDataRepository(
            localSource = get(),
            remoteSource = get()
        )
    }

    includes(
        userListModule,
        userDetailsModule,
        usersStatsModule
    )
}
