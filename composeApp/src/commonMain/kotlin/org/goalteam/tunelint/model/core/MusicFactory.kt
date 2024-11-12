package org.goalteam.tunelint.model.core

import org.goalteam.tunelint.model.core.impl.MeasureImpl
import org.goalteam.tunelint.model.core.impl.MelodyImpl
import org.goalteam.tunelint.model.core.impl.NoteImpl
import org.goalteam.tunelint.model.core.impl.RestImpl

class MusicFactory {
    fun createNote(
        pitch: Int,
        value: Int,
    ): Note = NoteImpl(pitch, value)

    fun createRest(length: Int): Rest = RestImpl(length)

    fun createMeasure(): Measure = MeasureImpl(mutableListOf())

    fun createMeasure(notes: List<Symbol>): MutableMeasure = MeasureImpl(notes.toMutableList())

    fun createMelody(
        name: String,
        measures: List<MutableMeasure>,
    ): MutableMelody = MelodyImpl(name, measures)
}
