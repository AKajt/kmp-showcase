package com.aljazkajtna.kmpshowcase.domain.local

data class UserLocalDomainModel(
    val id: String,
    val firstName: String,
    val lastName: String,
    val gender: Gender,
    val age: Long,
)
