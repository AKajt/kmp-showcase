package com.aljazkajtna.kmpshowcase.data.local

import com.aljazkajtna.kmpshowcase.domain.local.UserLocalDomainModel

interface LocalSource {

    suspend fun getAllUsers(): List<UserLocalDomainModel>

    suspend fun getUserById(userId: String): UserLocalDomainModel?

    suspend fun insertUser(user: UserLocalDomainModel)

    suspend fun updateUser(user: UserLocalDomainModel)

    suspend fun deleteUser(userId: String)

    suspend fun getAverageAge(): Double

    suspend fun getGenderCounts(): List<Int>
}
