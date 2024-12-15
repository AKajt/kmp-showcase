package com.aljazkajtna.kmpshowcase.domain

import com.aljazkajtna.kmpshowcase.domain.external.UserExternalDomainModel
import com.aljazkajtna.kmpshowcase.domain.local.UserLocalDomainModel

interface UsersRepository {

    suspend fun users() : List<UserLocalDomainModel>

    suspend fun getUserById(userId: String): UserLocalDomainModel?

    suspend fun insertUser(user: UserLocalDomainModel)

    suspend fun updateUser(user: UserLocalDomainModel)

    suspend fun deleteUser(userId: String)

    suspend fun getAverageAge(): Double

    suspend fun getGenderCounts(): List<Int>

    suspend fun externalUsers(): List<UserExternalDomainModel>
}
