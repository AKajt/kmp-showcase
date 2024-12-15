package com.aljazkajtna.kmpshowcase.data.remote.model

import com.aljazkajtna.kmpshowcase.domain.external.UserPostDomainModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserPostApiModel(
    @SerialName("userId")
    val userId: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("body")
    val body: String,
)

fun UserPostApiModel.toDomain() = UserPostDomainModel(
    userId = userId,
    id = id,
    title = title,
    body = body
)
