package com.aljazkajtna.kmpshowcase.data

import com.aljazkajtna.kmpshowcase.domain.model.UserDomainModel

interface LocalSource {
    suspend fun getAllUsers(): List<UserDomainModel>
    suspend fun insertUser(user: UserDomainModel)
}
