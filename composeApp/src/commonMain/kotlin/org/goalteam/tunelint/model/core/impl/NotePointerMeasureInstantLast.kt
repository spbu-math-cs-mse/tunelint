package org.goalteam.tunelint.model.core.impl

import org.goalteam.tunelint.model.core.Melody
import org.goalteam.tunelint.model.core.NotePointer

class NotePointerMeasureInstantLast(
    private val measure: Int,
) : NotePointer {
    private var initialized = false
    private var position: Int = 0

    private fun initialize(melody: Melody) {
        position = melody.measures[measure].symbols.lastIndex
        initialized = true
    }

    override fun measure(melody: Melody): Int = measure

    override fun position(melody: Melody): Int {
        if (!initialized) {
            initialize(melody)
        }
        return position
    }

    override fun next(): NotePointer = this

    override fun toString(): String = "last in measure $measure"
}
