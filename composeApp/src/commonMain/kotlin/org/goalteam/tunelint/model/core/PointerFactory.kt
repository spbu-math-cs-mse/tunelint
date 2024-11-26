package org.goalteam.tunelint.model.core

import org.goalteam.tunelint.model.core.impl.NotePointerMeasureEternalLast
import org.goalteam.tunelint.model.core.impl.NotePointerMelodyEternalLast
import org.goalteam.tunelint.model.core.impl.NotePointerSimple

class PointerFactory {
    fun createNotePointerSimple(
        measure: Int,
        position: Int,
    ): NotePointer = NotePointerSimple(measure, position)

    fun createNotePointerLastMelody(): NotePointer = NotePointerMelodyEternalLast()

    fun createNotePointerLastMeasure(measure: Int): NotePointer = NotePointerMeasureEternalLast(measure)
}
