package com.aljazkajtna.kmpshowcase.data.local.cache

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.aljazkajtna.kmpshowcase.cache.KMPShowcaseDatabase

class AndroidDatabaseDriverFactory(private val context: Context) : DatabaseDriverFactory {
    override fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(KMPShowcaseDatabase.Schema, context, "user.db")
    }
}
