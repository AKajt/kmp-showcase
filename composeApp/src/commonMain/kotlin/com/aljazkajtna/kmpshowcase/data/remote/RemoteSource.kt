package com.aljazkajtna.kmpshowcase.data.remote

import com.aljazkajtna.kmpshowcase.domain.external.UserExternalDomainModel

interface RemoteSource {

    suspend fun users(): List<UserExternalDomainModel>
}
