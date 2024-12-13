package com.aljazkajtna.kmpshowcase.core

import platform.Foundation.NSUUID

actual class NativeUtils actual constructor() {

    actual fun randomUUID(): String {
        return NSUUID().UUIDString()
    }
}
