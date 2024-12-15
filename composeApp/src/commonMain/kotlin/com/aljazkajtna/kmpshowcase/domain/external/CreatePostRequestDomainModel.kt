package com.aljazkajtna.kmpshowcase.domain.external

data class CreatePostRequestDomainModel(
    val userId: Int,
    val title: String,
    val body: String,
)
