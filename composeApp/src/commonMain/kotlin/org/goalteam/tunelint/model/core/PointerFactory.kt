package org.goalteam.tunelint.model.core

import org.goalteam.tunelint.model.core.impl.NotePointerLastMeasure
import org.goalteam.tunelint.model.core.impl.NotePointerLastMelody
import org.goalteam.tunelint.model.core.impl.NotePointerSimple

class PointerFactory {
    fun createNotePointerSimple(
        measure: Int,
        position: Int,
    ): NotePointer = NotePointerSimple(measure, position)

    fun createNotePointerLastMelody(): NotePointer = NotePointerLastMelody()

    fun createNotePointerLastMeasure(measure: Int): NotePointer = NotePointerLastMeasure(measure)
}
