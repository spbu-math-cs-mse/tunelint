package org.goalteam.tunelint.musicsheet

import org.goalteam.tunelint.filesupport.XMLParser
import org.goalteam.tunelint.model.changerequest.PersistenceManagerFactory
import org.goalteam.tunelint.model.core.Melody
import org.goalteam.tunelint.model.core.MusicFactory
import org.goalteam.tunelint.model.core.TimeSignature

class MusicSheet(
    val path: String,
) {
    val melody: Melody

    init {
        val parser = XMLParser()
        val musicFactory = MusicFactory()
        melody = parser.readMusicXML(path).firstOrNull()
            ?: musicFactory.createMelody("", TimeSignature.standardTime)
    }

    val persistenceManager = PersistenceManagerFactory().persistenceManager(melody)

    fun save(format: String) {
        val parser = XMLParser()
        parser.writeMusicXML(path, mutableListOf(melody))
    }
}
