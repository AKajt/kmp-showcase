package com.aljazkajtna.kmpshowcase.domain.external

data class UserPostDomainModel(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)
