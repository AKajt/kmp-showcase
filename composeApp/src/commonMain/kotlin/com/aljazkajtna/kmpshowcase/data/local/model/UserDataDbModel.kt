package com.aljazkajtna.kmpshowcase.data.local.model

import com.aljazkajtna.kmpshowcase.domain.local.Gender
import com.aljazkajtna.kmpshowcase.domain.local.UserLocalDomainModel

data class UserDataDbModel(
    val id: String,
    val firstName: String,
    val lastName: String,
    val gender: Gender,
    val age: Long,
)

fun UserDataDbModel.toDomain() = UserLocalDomainModel(
    id = id,
    firstName = firstName,
    lastName = lastName,
    gender = gender,
    age = age
)

fun UserLocalDomainModel.toDbModel() = UserDataDbModel(
    id = id,
    firstName = firstName,
    lastName = lastName,
    gender = gender,
    age = age
)
