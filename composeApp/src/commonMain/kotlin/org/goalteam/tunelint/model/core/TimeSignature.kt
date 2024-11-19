package org.goalteam.tunelint.model.core

class TimeSignature(
    val count: Int,
    val primary: PrimaryNoteValue,
) {
    companion object {
        val standardTime = TimeSignature(2, PrimaryNoteValue(-1))
    }

    init {
        require(count > 0)
        require(primary <= PrimaryNoteValue.Whole)
    }

    fun value() = primary.value() * count

    override fun toString() = "$count : ${primary.denominator()}"
}
