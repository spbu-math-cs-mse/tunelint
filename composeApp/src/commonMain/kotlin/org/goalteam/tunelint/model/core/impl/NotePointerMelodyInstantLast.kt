package org.goalteam.tunelint.model.core.impl

import org.goalteam.tunelint.model.core.Melody
import org.goalteam.tunelint.model.core.NotePointer

class NotePointerMelodyInstantLast : NotePointer {
    private var measure: Int = 0
    private var position: Int = 0
    private var initialized = false

    private fun initialize(melody: Melody) {
        initialized = true
        measure = melody.measures.lastIndex
        position =
            melody.measures
                .last()
                .symbols.lastIndex
    }

    override fun measure(melody: Melody): Int {
        if (!initialized) {
            initialize(melody)
        }
        return measure
    }

    override fun position(melody: Melody): Int {
        if (!initialized) {
            initialize(melody)
        }
        return position
    }

    override fun next(): NotePointer = this

    override fun toString(): String = "the last available"
}
