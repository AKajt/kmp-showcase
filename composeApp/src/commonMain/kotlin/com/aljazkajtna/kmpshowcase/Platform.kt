package com.aljazkajtna.kmpshowcase

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform