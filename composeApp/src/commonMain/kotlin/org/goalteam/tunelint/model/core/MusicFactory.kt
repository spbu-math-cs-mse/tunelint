package org.goalteam.tunelint.model.core

import org.goalteam.tunelint.model.core.impl.EmptyImpl
import org.goalteam.tunelint.model.core.impl.MeasureImpl
import org.goalteam.tunelint.model.core.impl.NoteImpl

class MusicFactory {
    fun createNote(
        value: Int,
        length: Float,
    ): Note = NoteImpl(value, length)

    fun createEmpty(length: Float): Empty = EmptyImpl(length)

    fun createMeasure(): Measure = MeasureImpl(mutableListOf())

    fun createMeasure(notes: List<Symbol>): Measure = MeasureImpl(notes.toMutableList())
}
