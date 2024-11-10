package org.goalteam.tunelint.model.core.impl

import org.goalteam.tunelint.model.core.Note

internal class NoteImpl(
    private val pitch: Int,
    private val value: Float,
) : Note {
    override fun pitch() = pitch

    override fun value() = value

    override fun equals(other: Any?): Boolean =
        other != null &&
            other is Note &&
            other.pitch() == pitch &&
            other.value() == value
}
