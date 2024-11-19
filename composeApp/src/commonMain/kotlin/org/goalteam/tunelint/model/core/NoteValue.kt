package org.goalteam.tunelint.model.core

class NoteValue(
    val value: Int,
) : Comparable<NoteValue> {
    companion object {
        val Nil = NoteValue(0)
    }

    operator fun plus(other: NoteValue) = NoteValue(value + other.value)

    operator fun minus(other: NoteValue) = NoteValue(value - other.value)

    operator fun times(other: Int) = NoteValue(value * other)

    operator fun div(other: Int) = NoteValue(value / other)

    operator fun div(other: NoteValue) = value / other.value

    override fun equals(other: Any?) =
        other != null &&
            other is NoteValue &&
            compareTo(other) == 0

    override fun hashCode() = value.hashCode()

    override fun compareTo(other: NoteValue) = value.compareTo(other.value)
}
