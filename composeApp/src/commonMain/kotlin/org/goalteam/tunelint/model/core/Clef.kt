package org.goalteam.tunelint.model.core

class Clef(
    val type: ClefType,
) {
    fun bottomLineNote() = clefNote[type]

    enum class ClefType {
        G,
        C,
        F,
    }

    private val clefNote =
        mapOf(
            ClefType.G to NaturalOctavedNote(NaturalNote(2), 4),
            ClefType.C to NaturalOctavedNote(NaturalNote(1), 3),
            ClefType.F to NaturalOctavedNote(NaturalNote(4), 2),
        )
}
