package com.aljazkajtna.kmpshowcase.domain.model

interface UsersRepository {

    suspend fun users() : List<UserDomainModel>

    suspend fun getUserById(userId: String): UserDomainModel?

    suspend fun insertUser(user: UserDomainModel)

    suspend fun updateUser(user: UserDomainModel)

    suspend fun deleteUser(userId: String)

    suspend fun getAverageAge(): Double

    suspend fun getGenderCounts(): List<Int>
}
