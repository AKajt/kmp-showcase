package com.aljazkajtna.kmpshowcase.data.remote

import com.aljazkajtna.kmpshowcase.domain.external.UserExternalDomainModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class RemoteDataSource(
    private val baseUrl: String,
    private val httpClient: HttpClient
) : RemoteSource {

    override suspend fun users(): List<UserExternalDomainModel> {
        return httpClient.get(baseUrl + "users").body()
    }
}
