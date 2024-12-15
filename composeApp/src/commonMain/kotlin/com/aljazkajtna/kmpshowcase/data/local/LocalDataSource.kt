package com.aljazkajtna.kmpshowcase.data.local

import com.aljazkajtna.kmpshowcase.data.LocalSource
import com.aljazkajtna.kmpshowcase.data.local.cache.Database
import com.aljazkajtna.kmpshowcase.data.local.model.toDbModel
import com.aljazkajtna.kmpshowcase.data.local.model.toDomain
import com.aljazkajtna.kmpshowcase.domain.model.UserDomainModel

class LocalDataSource(
    private val database: Database
) : LocalSource {

    override suspend fun getAllUsers(): List<UserDomainModel> {
        return database.getAllUsers().map {
            it.toDomain()
        }
    }

    override suspend fun getUserById(userId: String): UserDomainModel? {
        return database.selectUserById(userId)?.toDomain()
    }

    override suspend fun insertUser(user: UserDomainModel) {
        database.insertUser(user.toDbModel())
    }

    override suspend fun updateUser(user: UserDomainModel) {
        database.updateUserById(user.toDbModel())
    }

    override suspend fun deleteUser(userId: String) {
        database.deleteUser(userId)
    }
}
