package com.aljazkajtna.kmpshowcase.data.local.model

import com.aljazkajtna.kmpshowcase.domain.model.Gender
import com.aljazkajtna.kmpshowcase.domain.model.UserDomainModel

data class UserDataDbModel(
    val id: String,
    val firstName: String,
    val lastName: String,
    val gender: Gender,
    val age: Long,
)

fun UserDataDbModel.toDomain() = UserDomainModel(
    id = id,
    firstName = firstName,
    lastName = lastName,
    gender = gender,
    age = age
)

fun UserDomainModel.toDbModel() = UserDataDbModel(
    id = id,
    firstName = firstName,
    lastName = lastName,
    gender = gender,
    age = age
)
