package org.goalteam.tunelint.model.core

class NoteOffset(
    val value: Int,
) {
    operator fun plus(other: NoteOffset) = NoteOffset(value + other.value)

    override fun equals(other: Any?) =
        other != null &&
            other is NoteOffset &&
            other.value == value

    override fun hashCode() = value.hashCode()
}
