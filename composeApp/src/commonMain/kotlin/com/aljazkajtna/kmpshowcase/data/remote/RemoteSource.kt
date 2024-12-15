package com.aljazkajtna.kmpshowcase.data.remote

import com.aljazkajtna.kmpshowcase.domain.external.UserExternalDomainModel
import com.aljazkajtna.kmpshowcase.domain.external.UserPostDomainModel

interface RemoteSource {

    suspend fun users(): List<UserExternalDomainModel>

    suspend fun userPosts(userId: Int): List<UserPostDomainModel>

}
