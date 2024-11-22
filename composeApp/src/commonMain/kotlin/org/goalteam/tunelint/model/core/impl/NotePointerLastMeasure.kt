package org.goalteam.tunelint.model.core.impl

import org.goalteam.tunelint.model.core.Melody
import org.goalteam.tunelint.model.core.NotePointer

class NotePointerLastMeasure(
    private val measure: Int,
) : NotePointer {
    override fun measure(melody: Melody): Int = measure

    override fun position(melody: Melody): Int = melody.measures[measure].symbols.lastIndex

    override fun next(): NotePointer = this

    override fun toString(): String = "last in measure $measure"
}
