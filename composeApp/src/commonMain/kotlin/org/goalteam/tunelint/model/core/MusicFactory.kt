package org.goalteam.tunelint.model.core

import org.goalteam.tunelint.model.core.impl.MeasureImpl
import org.goalteam.tunelint.model.core.impl.NoteImpl
import org.goalteam.tunelint.model.core.impl.RestImpl

class MusicFactory {
    fun createNote(
        pitch: Int,
        value: Float,
    ): Note = NoteImpl(pitch, value)

    fun createEmpty(length: Float): Rest = RestImpl(length)

    fun createMeasure(): Measure = MeasureImpl(mutableListOf())

    fun createMeasure(notes: List<Symbol>): Measure = MeasureImpl(notes.toMutableList())
}
