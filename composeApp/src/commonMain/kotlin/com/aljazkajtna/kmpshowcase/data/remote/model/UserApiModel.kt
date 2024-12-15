package com.aljazkajtna.kmpshowcase.data.remote.model

import com.aljazkajtna.kmpshowcase.domain.external.UserExternalDomainModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserApiModel(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("username")
    val username: String,
    @SerialName("email")
    val email: String,
    @SerialName("phone")
    val phone: String,
    @SerialName("website")
    val website: String,
)

fun UserApiModel.toDomain(): UserExternalDomainModel {
    return UserExternalDomainModel(
        id = id,
        name = name,
        username = username,
        email = email,
        phone = phone,
        website = website
    )
}
