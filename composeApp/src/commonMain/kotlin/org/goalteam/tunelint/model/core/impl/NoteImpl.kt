package org.goalteam.tunelint.model.core.impl

import org.goalteam.tunelint.model.core.Accidental
import org.goalteam.tunelint.model.core.Note
import org.goalteam.tunelint.model.core.NoteOffset
import org.goalteam.tunelint.model.core.PrimaryNoteValue

internal class NoteImpl(
    private val stage: NoteOffset,
    private val primaryValue: PrimaryNoteValue,
    private val accidental: Accidental?,
) : Note {
    override fun stage() = stage

    override fun value() = primaryValue.value()

    override fun primaryValue() = primaryValue

    override fun accidental() = accidental

    override fun clone() = NoteImpl(stage, primaryValue, accidental)

    override fun equals(other: Any?): Boolean =
        other != null &&
            other is Note &&
            other.stage() == stage &&
            other.value() == value()

    override fun hashCode() = stage.hashCode() xor value().hashCode()
}
