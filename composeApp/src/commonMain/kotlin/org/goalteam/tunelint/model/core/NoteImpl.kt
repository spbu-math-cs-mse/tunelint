package org.goalteam.tunelint.model.core

internal class NoteImpl(
    private val value: Int,
) : Note {
    override fun value() = value
}
