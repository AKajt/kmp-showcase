package com.aljazkajtna.kmpshowcase.data.remote.model

import com.aljazkajtna.kmpshowcase.domain.external.CreatePostRequestDomainModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreatePostRequestApiModel(
    @SerialName("userId")
    val userId: Int,
    @SerialName("title")
    val title: String,
    @SerialName("body")
    val body: String,
)

fun CreatePostRequestDomainModel.toApi() = CreatePostRequestApiModel(
    userId = userId,
    title = title,
    body = body
)
