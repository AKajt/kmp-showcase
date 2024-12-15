package com.aljazkajtna.kmpshowcase.data

import com.aljazkajtna.kmpshowcase.domain.model.UserDomainModel

interface LocalSource {

    suspend fun getAllUsers(): List<UserDomainModel>

    suspend fun getUserById(userId: String): UserDomainModel?

    suspend fun insertUser(user: UserDomainModel)

    suspend fun updateUser(user: UserDomainModel)

    suspend fun deleteUser(userId: String)

    suspend fun getAverageAge(): Double

    suspend fun getGenderCounts(): List<Int>
}
