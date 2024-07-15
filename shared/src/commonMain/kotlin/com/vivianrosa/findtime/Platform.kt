package com.vivianrosa.findtime

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform