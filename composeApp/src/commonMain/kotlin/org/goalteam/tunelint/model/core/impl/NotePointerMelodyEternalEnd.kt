package org.goalteam.tunelint.model.core.impl

import org.goalteam.tunelint.model.core.Melody
import org.goalteam.tunelint.model.core.NotePointer

class NotePointerMelodyEternalEnd : NotePointer {
    override fun measure(melody: Melody) = melody.measures.lastIndex

    override fun position(melody: Melody) =
        melody.measures
            .last()
            .symbols.lastIndex + 1

    override fun next(): NotePointer = this

    override fun toString() = "one after the last"
}
