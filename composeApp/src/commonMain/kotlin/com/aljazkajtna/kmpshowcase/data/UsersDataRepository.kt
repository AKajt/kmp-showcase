package com.aljazkajtna.kmpshowcase.data

import com.aljazkajtna.kmpshowcase.data.local.LocalSource
import com.aljazkajtna.kmpshowcase.data.remote.RemoteSource
import com.aljazkajtna.kmpshowcase.domain.local.UserLocalDomainModel
import com.aljazkajtna.kmpshowcase.domain.UsersRepository
import com.aljazkajtna.kmpshowcase.domain.external.CreatePostRequestDomainModel
import com.aljazkajtna.kmpshowcase.domain.external.UserExternalDomainModel
import com.aljazkajtna.kmpshowcase.domain.external.UserPostDomainModel

class UsersDataRepository(
    private val localSource: LocalSource,
    private val remoteSource: RemoteSource
) : UsersRepository {

    override suspend fun users(): List<UserLocalDomainModel> {
        return localSource.getAllUsers()
    }

    override suspend fun getUserById(userId: String): UserLocalDomainModel? {
        return localSource.getUserById(userId)
    }

    override suspend fun insertUser(user: UserLocalDomainModel) {
        return localSource.insertUser(user)
    }

    override suspend fun updateUser(user: UserLocalDomainModel) {
        return localSource.updateUser(user)
    }

    override suspend fun deleteUser(userId: String) {
        return localSource.deleteUser(userId)
    }

    override suspend fun getAverageAge(): Double {
        return localSource.getAverageAge()
    }

    override suspend fun getGenderCounts(): List<Int> {
        return localSource.getGenderCounts()
    }

    override suspend fun externalUsers(): List<UserExternalDomainModel> {
        return remoteSource.users()
    }

    override suspend fun userPosts(userId: Int): List<UserPostDomainModel> {
        return remoteSource.userPosts(userId)
    }

    override suspend fun createPost(request: CreatePostRequestDomainModel) {
        remoteSource.createPost(request)
    }
}
