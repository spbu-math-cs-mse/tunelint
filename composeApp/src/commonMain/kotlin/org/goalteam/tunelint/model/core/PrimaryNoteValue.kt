package org.goalteam.tunelint.model.core

class PrimaryNoteValue(
    private val order: Int,
) : Comparable<PrimaryNoteValue> {
    companion object {
        private const val MAXIMUM_ORDER = 3
        private const val MINIMUM_ORDER = -8

        val Double = PrimaryNoteValue(1)
        val Whole = PrimaryNoteValue(0)
        val Half = PrimaryNoteValue(-1)
        val Quarter = PrimaryNoteValue(-2)
        val Eighth = PrimaryNoteValue(-3)
        val Sixteenth = PrimaryNoteValue(-4)
    }

    init {
        require(order in MINIMUM_ORDER..MAXIMUM_ORDER)
    }

    fun value() = NoteValue(1 shl (order - MINIMUM_ORDER))

    fun next() = PrimaryNoteValue(order + 1)

    fun prev() = PrimaryNoteValue(order - 1)

    fun denominator(): Int {
        if (order > 0) {
            throw
            UnsupportedOperationException(
                "cannot take denominator of operators, more than a full note",
            )
        }

        return 1 shl (-order)
    }

    override fun compareTo(other: PrimaryNoteValue) = order.compareTo(other.order)
}
