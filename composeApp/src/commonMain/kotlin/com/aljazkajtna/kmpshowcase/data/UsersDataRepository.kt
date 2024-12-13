package com.aljazkajtna.kmpshowcase.data

import com.aljazkajtna.kmpshowcase.domain.model.UserDomainModel
import com.aljazkajtna.kmpshowcase.domain.model.UsersRepository

class UsersDataRepository(
    private val localSource: LocalSource
) : UsersRepository {

    override suspend fun users(): List<UserDomainModel> {
        return localSource.getAllUsers()
    }

    override suspend fun insertUser(user: UserDomainModel) {
        return localSource.insertUser(user)
    }
}
