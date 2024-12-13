package com.aljazkajtna.kmpshowcase.domain.model

interface UsersRepository {

    suspend fun users() : List<UserDomainModel>

    suspend fun insertUser(user: UserDomainModel)
}
