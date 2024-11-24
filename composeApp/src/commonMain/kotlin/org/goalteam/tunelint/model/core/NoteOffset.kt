package org.goalteam.tunelint.model.core

class NoteOffset(
    val value: Int,
) {
    operator fun plus(other: NoteOffset) = NoteOffset(value + other.value)
}
