package org.goalteam.tunelint.model.core.impl

import org.goalteam.tunelint.model.core.Melody
import org.goalteam.tunelint.model.core.NotePointer

class NotePointerSimple(
    private val measure: Int,
    private val position: Int,
) : NotePointer {
    override fun measure(melody: Melody): Int = measure

    override fun position(melody: Melody): Int = position

    override fun next(): NotePointer = NotePointerSimple(measure, position + 1)

    override fun toString(): String = "measure=$measure, position=$position"
}
