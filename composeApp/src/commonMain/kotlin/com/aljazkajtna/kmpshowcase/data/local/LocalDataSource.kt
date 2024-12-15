package com.aljazkajtna.kmpshowcase.data.local

import com.aljazkajtna.kmpshowcase.data.local.cache.Database
import com.aljazkajtna.kmpshowcase.data.local.model.toDbModel
import com.aljazkajtna.kmpshowcase.data.local.model.toDomain
import com.aljazkajtna.kmpshowcase.domain.local.UserLocalDomainModel

class LocalDataSource(
    private val database: Database
) : LocalSource {

    override suspend fun getAllUsers(): List<UserLocalDomainModel> {
        return database.getAllUsers().map {
            it.toDomain()
        }
    }

    override suspend fun getUserById(userId: String): UserLocalDomainModel? {
        return database.selectUserById(userId)?.toDomain()
    }

    override suspend fun insertUser(user: UserLocalDomainModel) {
        database.insertUser(user.toDbModel())
    }

    override suspend fun updateUser(user: UserLocalDomainModel) {
        database.updateUserById(user.toDbModel())
    }

    override suspend fun deleteUser(userId: String) {
        database.deleteUser(userId)
    }

    override suspend fun getAverageAge(): Double {
        return database.getAverageAge() ?: 0.0
    }

    override suspend fun getGenderCounts(): List<Int> {
        return database.getGenderCounts()
    }
}
