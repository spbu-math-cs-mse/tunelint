package org.goalteam.tunelint

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform