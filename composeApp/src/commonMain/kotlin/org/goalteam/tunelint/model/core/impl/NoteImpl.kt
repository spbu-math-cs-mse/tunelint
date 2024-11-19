package org.goalteam.tunelint.model.core.impl

import org.goalteam.tunelint.model.core.Note
import org.goalteam.tunelint.model.core.PrimaryNoteValue

internal class NoteImpl(
    private val stage: Int,
    private val primaryValue: PrimaryNoteValue,
) : Note {
    override fun stage() = stage

    override fun value() = primaryValue.value()

    override fun primaryValue() = primaryValue

    override fun clone() = NoteImpl(stage, primaryValue)

    override fun equals(other: Any?): Boolean =
        other != null &&
            other is Note &&
            other.stage() == stage &&
            other.value() == value()

    override fun hashCode() = stage.hashCode() xor value().hashCode()
}
