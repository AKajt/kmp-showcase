package com.aljazkajtna.kmpshowcase.data

import com.aljazkajtna.kmpshowcase.domain.model.UserDomainModel
import com.aljazkajtna.kmpshowcase.domain.model.UsersRepository

class UsersDataRepository(
    private val localSource: LocalSource
) : UsersRepository {

    override suspend fun users(): List<UserDomainModel> {
        return localSource.getAllUsers()
    }

    override suspend fun getUserById(userId: String): UserDomainModel? {
        return localSource.getUserById(userId)
    }

    override suspend fun insertUser(user: UserDomainModel) {
        return localSource.insertUser(user)
    }

    override suspend fun updateUser(user: UserDomainModel) {
        return localSource.updateUser(user)
    }

    override suspend fun deleteUser(userId: String) {
        return localSource.deleteUser(userId)
    }
}
