package org.goalteam.tunelint.filesupport

import org.goalteam.tunelint.model.core.Melody

interface Parser {
    fun readMusic(path: String): List<Melody>

    fun writeMusic(
        path: String,
        melodyList: List<Melody>,
    )
}
