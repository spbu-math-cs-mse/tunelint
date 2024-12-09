package org.goalteam.tunelint.musicsheet

import org.goalteam.tunelint.filesupport.Parser
import org.goalteam.tunelint.filesupport.ParserFactory
import org.goalteam.tunelint.filesupport.ParserProperty
import org.goalteam.tunelint.model.changerequest.PersistenceManagerFactory
import org.goalteam.tunelint.model.core.Melody
import org.goalteam.tunelint.model.core.MusicFactory
import org.goalteam.tunelint.model.core.TimeSignature
import org.goalteam.tunelint.model.core.syncWith
import org.goalteam.tunelint.property.PathProperty
import org.goalteam.tunelint.property.Property
import java.io.IOException

class MusicSheet(
    private val path: String,
) {
    private val melody = MusicFactory().createMelody("", TimeSignature.standardTime)
    private var modified = true
    val persistenceManager = PersistenceManagerFactory().persistenceManager(melody)

    /**
     * @param properties collection of properties, for example ParserProperty
     */
    fun load(properties: Collection<Property<*>>) {
        val read = parser(properties).readMusic(path).firstOrNull()
        if (read != null) {
            melody.syncWith(read)
        } else {
            throw IOException("Couldn't load $path")
        }
    }

    /**
     * @param properties collection of properties, for example ParserProperty
     */
    fun save(properties: Collection<Property<*>>) {
        parser(properties).writeMusic(
            path(properties),
            mutableListOf(melody),
        )
    }

    fun modified() = modified

    fun makeDirty() {
        modified = true
    }

    fun location() = path

    private fun parser(properties: Collection<Property<*>>): Parser =
        properties.filterIsInstance<ParserProperty>().firstOrNull()?.value() ?: ParserFactory().xml().value()

    private fun path(properties: Collection<Property<*>>): String =
        properties.filterIsInstance<PathProperty>().firstOrNull()?.value() ?: path

    fun contents(): Melody = melody
}
