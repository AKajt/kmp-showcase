package com.aljazkajtna.kmpshowcase.ui.model

import com.aljazkajtna.kmpshowcase.domain.local.Gender
import com.aljazkajtna.kmpshowcase.domain.local.UserLocalDomainModel

data class UserLocalUiModel(
    val id: String,
    val firstName: String,
    val lastName: String,
    val gender: Gender,
    val age: Long,
)

fun UserLocalDomainModel.toUi() = UserLocalUiModel(
    id = id,
    firstName = firstName,
    lastName = lastName,
    gender = gender,
    age = age,
)
