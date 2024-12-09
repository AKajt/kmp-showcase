package com.aljazkajtna.kmpshowcase.data.local.cache

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.aljazkajtna.kmpshowcase.cache.KMPShowcaseDatabase

class IOSDatabaseDriverFactory : DatabaseDriverFactory {
    override fun createDriver(): SqlDriver {
        return NativeSqliteDriver(KMPShowcaseDatabase.Schema, "user.db")
    }
}
