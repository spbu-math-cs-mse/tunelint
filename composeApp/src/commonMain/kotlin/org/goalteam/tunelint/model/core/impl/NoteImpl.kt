package org.goalteam.tunelint.model.core.impl

import org.goalteam.tunelint.model.core.Note

internal class NoteImpl(
    private val value: Int,
    private val length: Float,
) : Note {
    override fun value() = value

    override fun length() = length

    override fun equals(other: Any?): Boolean =
        other != null &&
            other is Note &&
            other.value() == value &&
            other.length() == length
}
