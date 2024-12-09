package com.aljazkajtna.kmpshowcase.domain.model

interface UsersRepository {

    suspend fun users() : List<UserDomainModel>
}
