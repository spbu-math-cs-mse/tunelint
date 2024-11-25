package org.goalteam.tunelint.model.core.impl

import org.goalteam.tunelint.model.core.Melody
import org.goalteam.tunelint.model.core.NotePointer

class NotePointerMelodyEternalLast : NotePointer {
    override fun measure(melody: Melody): Int = melody.measures.lastIndex

    override fun position(melody: Melody): Int =
        melody.measures
            .last()
            .symbols.lastIndex

    override fun next(): NotePointer = this

    override fun toString(): String = "the last available"
}
