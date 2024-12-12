package com.aljazkajtna.kmpshowcase.data.local

import com.aljazkajtna.kmpshowcase.data.LocalSource
import com.aljazkajtna.kmpshowcase.data.local.cache.Database
import com.aljazkajtna.kmpshowcase.data.local.model.toDomain
import com.aljazkajtna.kmpshowcase.domain.model.UserDomainModel

class LocalDataSource(
    private val database: Database
): LocalSource {
    override suspend fun getAllUsers(): List<UserDomainModel> {
        return database.getAllUsers().map {
            it.toDomain()
        }
    }
}
