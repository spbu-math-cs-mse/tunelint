package org.goalteam.tunelint.model.core

class NaturalNote(
    private val value: Int,
) {
    init {
        require(value >= 0 && value < Constants.OCTAVE_NOTES)
    }

    private val stringVal =
        mapOf(
            0 to "C",
            1 to "D",
            2 to "E",
            3 to "F",
            4 to "G",
            5 to "A",
            6 to "B",
        )

    private val pitchVal =
        mapOf(
            0 to 0,
            1 to 2,
            2 to 4,
            3 to 5,
            4 to 7,
            5 to 9,
            6 to 11,
        )

    override fun toString(): String = stringVal[value]!!

    fun pitchOffset() = pitchVal[value]!!
}
