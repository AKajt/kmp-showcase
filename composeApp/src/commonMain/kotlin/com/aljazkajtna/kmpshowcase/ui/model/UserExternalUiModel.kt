package com.aljazkajtna.kmpshowcase.ui.model

import com.aljazkajtna.kmpshowcase.domain.external.UserExternalDomainModel
import com.aljazkajtna.kmpshowcase.domain.local.Gender
import com.aljazkajtna.kmpshowcase.domain.local.UserLocalDomainModel

data class UserExternalUiModel(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    val website: String,
)

fun UserExternalDomainModel.toUi() = UserExternalUiModel(
    id = id,
    name = name,
    username = username,
    email = email,
    phone = phone,
    website = website,
)
