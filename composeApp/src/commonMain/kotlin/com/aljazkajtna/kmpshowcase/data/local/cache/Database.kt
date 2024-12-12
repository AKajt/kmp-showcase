package com.aljazkajtna.kmpshowcase.data.local.cache

import app.cash.sqldelight.EnumColumnAdapter
import com.aljazkajtna.kmpshowcase.cache.KMPShowcaseDatabase
import com.aljazkajtna.kmpshowcase.cache.User
import com.aljazkajtna.kmpshowcase.data.local.model.UserDataDbModel
import com.aljazkajtna.kmpshowcase.domain.model.Gender

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
                    lastName = user. lastName,
                    gender = user.gender,
                    age = user.age
                )
            }
        }
    }
}
