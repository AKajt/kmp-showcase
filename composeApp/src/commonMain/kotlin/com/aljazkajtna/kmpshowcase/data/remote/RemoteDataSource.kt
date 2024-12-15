package com.aljazkajtna.kmpshowcase.data.remote

import com.aljazkajtna.kmpshowcase.data.remote.model.UserApiModel
import com.aljazkajtna.kmpshowcase.data.remote.model.UserPostApiModel
import com.aljazkajtna.kmpshowcase.data.remote.model.toApi
import com.aljazkajtna.kmpshowcase.data.remote.model.toDomain
import com.aljazkajtna.kmpshowcase.domain.external.CreatePostRequestDomainModel
import com.aljazkajtna.kmpshowcase.domain.external.UserExternalDomainModel
import com.aljazkajtna.kmpshowcase.domain.external.UserPostDomainModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class RemoteDataSource(
    private val baseUrl: String,
    private val httpClient: HttpClient
) : RemoteSource {

    override suspend fun users(): List<UserExternalDomainModel> {
        val body = httpClient
            .get(baseUrl + "users")
            .body<List<UserApiModel>>()
        return body.map { it.toDomain() }
    }

    override suspend fun userPosts(userId: Int): List<UserPostDomainModel> {
        val body = httpClient
            .get(baseUrl + "users/${userId}/posts")
            .body<List<UserPostApiModel>>()
        return body.map { it.toDomain() }
    }

    override suspend fun createPost(request: CreatePostRequestDomainModel) {
        val apiRequest = request.toApi()
        httpClient
            .post(baseUrl + "posts") {
                contentType(ContentType.Application.Json)
                setBody(apiRequest)
            }
    }
}
