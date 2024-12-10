package org.goalteam.tunelint.filesupport

import org.goalteam.tunelint.model.core.Clef
import org.goalteam.tunelint.model.core.Melody
import org.goalteam.tunelint.model.core.MusicFactory
import org.goalteam.tunelint.model.core.TimeSignature
import java.io.File

class FakeParser : Parser {
    override fun readMusic(path: String): List<Melody> =
        listOf(
            MusicFactory().createMelody("Dora Dura", Clef(Clef.ClefType.G), TimeSignature.standardTime),
        )

    override fun writeMusic(
        path: String,
        melodyList: List<Melody>,
    ) {
        File(path).writeText(melodyList.toString())
    }
}
