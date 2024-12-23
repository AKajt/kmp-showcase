package com.aljazkajtna.kmpshowcase.data.local.cache

import app.cash.sqldelight.EnumColumnAdapter
import com.aljazkajtna.kmpshowcase.cache.KMPShowcaseDatabase
import com.aljazkajtna.kmpshowcase.cache.User
import com.aljazkajtna.kmpshowcase.data.local.model.UserDataDbModel
import com.aljazkajtna.kmpshowcase.domain.local.Gender

class Database(
    databaseDriverFactory: DatabaseDriverFactory
) {
    private val database = KMPShowcaseDatabase(
        driver = databaseDriverFactory.createDriver(),
        UserAdapter = User.Adapter(
            genderAdapter = EnumColumnAdapter()
        )
    )
    private val dbQuery = database.kMPShowcaseDatabaseQueries

    internal fun getAllUsers(): List<UserDataDbModel> {
        return dbQuery.selectAllUsers(::mapUserSelecting).executeAsList()
    }

    private fun mapUserSelecting(
        id: String,
        firstName: String,
        lastName: String,
        gender: Gender,
        age: Long
    ): UserDataDbModel {
        return UserDataDbModel(
            id = id,
            firstName = firstName,
            lastName = lastName,
            gender = gender,
            age = age
        )
    }

    internal fun clearAndCreateUsers(launches: List<UserDataDbModel>) {
        dbQuery.transaction {
            dbQuery.removeAllUsers()
            launches.forEach { user ->
                dbQuery.insertUser(
                    id = user.id,
                    firstName = user.firstName,
                    lastName = user.lastName,
                    gender = user.gender,
                    age = user.age
                )
            }
        }
    }

    internal fun insertUser(user: UserDataDbModel) {
        dbQuery.insertUser(
            id = user.id,
            firstName = user.firstName,
            lastName = user.lastName,
            gender = user.gender,
            age = user.age
        )
    }

    internal fun selectUserById(userId: String): UserDataDbModel? {
        return dbQuery.selectUserById(userId)
            .executeAsOneOrNull()?.let {
                mapUserSelecting(
                    id = it.id,
                    firstName = it.firstName,
                    lastName = it.lastName,
                    gender = it.gender,
                    age = it.age
                )
            }
    }

    internal fun updateUserById(user: UserDataDbModel) {
        dbQuery.updateUserById(
            firstName = user.firstName,
            lastName = user.lastName,
            gender = user.gender,
            age = user.age,
            id = user.id
        )
    }

    internal fun deleteUser(id: String) {
        dbQuery.deleteUserById(id)
    }

    internal fun getAverageAge(): Double? {
        return dbQuery.getAverageAge().executeAsOneOrNull()?.AVG
    }

    internal fun getGenderCounts(): List<Int> {
        return dbQuery.getGenderCounts().executeAsOne().let {
            listOf(
                it.maleCount?.toInt() ?: 0,
                it.femaleCount?.toInt() ?: 0,
                it.otherCount?.toInt() ?: 0,
            )
        }
    }
}
