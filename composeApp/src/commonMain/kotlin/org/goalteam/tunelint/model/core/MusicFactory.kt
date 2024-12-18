package org.goalteam.tunelint.model.core

import org.goalteam.tunelint.model.core.impl.MeasureImpl
import org.goalteam.tunelint.model.core.impl.MelodyImpl
import org.goalteam.tunelint.model.core.impl.NoteImpl
import org.goalteam.tunelint.model.core.impl.RestImpl

class MusicFactory {
    fun createNote(
        stage: NoteOffset,
        primaryValue: PrimaryNoteValue,
    ): Note = NoteImpl(stage, primaryValue)

    fun createRest(primaryValue: PrimaryNoteValue): Rest = RestImpl(primaryValue)

    fun createMeasure(timeSignature: TimeSignature): Measure =
        MeasureImpl(
            timeSignature,
            mutableListOf(),
        )

    fun createMeasure(
        timeSignature: TimeSignature,
        notes: Collection<Symbol>,
    ): Measure = MeasureImpl(timeSignature, notes)

    fun createMelody(
        name: String,
        clef: Clef,
        timeSignature: TimeSignature,
    ): Melody = MelodyImpl(name, clef, timeSignature, listOf())

    fun createMelody(
        name: String,
        clef: Clef,
        timeSignature: TimeSignature,
        measures: Collection<Measure>,
    ): Melody = MelodyImpl(name, clef, timeSignature, measures)
}
