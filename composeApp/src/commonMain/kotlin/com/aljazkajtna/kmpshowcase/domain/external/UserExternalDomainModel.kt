package com.aljazkajtna.kmpshowcase.domain.external

data class UserExternalDomainModel(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    val website: String,
)
