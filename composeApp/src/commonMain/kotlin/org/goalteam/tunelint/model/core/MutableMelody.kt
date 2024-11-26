package org.goalteam.tunelint.model.core

interface MutableMelody {
    fun setName(name: String)

    fun addMeasure(
        position: Int,
        measure: Measure,
    )

    fun removeMeasure(position: Int): Boolean

    fun setMeasures(measures: Collection<Measure>)

    fun setTimeSignature(timeSignature: TimeSignature)

    fun mutableMeasures(): List<Measure>

    fun mutateMeasures(block: (List<Measure>) -> Unit)
}
