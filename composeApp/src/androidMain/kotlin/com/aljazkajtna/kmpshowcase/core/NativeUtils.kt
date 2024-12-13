package com.aljazkajtna.kmpshowcase.core

import java.util.UUID

actual class NativeUtils actual constructor() {

    actual fun randomUUID() : String {
        return UUID.randomUUID().toString()
    }
}
