package com.aljazkajtna.kmpshowcase.users.domain

import com.aljazkajtna.kmpshowcase.domain.model.UserDomainModel

interface UsersRepository {

    suspend fun users() : List<UserDomainModel>
}
