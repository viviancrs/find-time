package com.vivianrosa.findtime.logging

import io.github.aakira.napier.Napier

class Log {
    companion object {
        fun d(message: String) {
            Napier.d(message)
        }
    }
}