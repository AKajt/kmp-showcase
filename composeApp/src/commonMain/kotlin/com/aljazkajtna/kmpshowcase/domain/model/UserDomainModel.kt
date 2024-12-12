package com.aljazkajtna.kmpshowcase.domain.model

data class UserDomainModel(
    val id: String,
    val firstName: String,
    val lastName: String,
    val gender: Gender,
    val age: Long,
)
