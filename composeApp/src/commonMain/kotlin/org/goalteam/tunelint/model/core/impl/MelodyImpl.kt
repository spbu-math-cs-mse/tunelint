package org.goalteam.tunelint.model.core.impl

import org.goalteam.tunelint.model.core.Clef
import org.goalteam.tunelint.model.core.Measure
import org.goalteam.tunelint.model.core.Melody
import org.goalteam.tunelint.model.core.TimeSignature

internal class MelodyImpl(
    name: String,
    clef: Clef,
    timeSignature: TimeSignature,
    measures: Collection<Measure>,
) : Melody {
    private var _name = name
    private var _clef = clef
    private var _timeSignature = timeSignature
    private val _measures: MutableList<Measure> = measures.toMutableList()

    override fun clone() =
        MelodyImpl(
            name,
            clef,
            timeSignature,
            _measures.toList(),
        )

    override val name get() = _name
    override val clef get() = _clef
    override val timeSignature get() = _timeSignature
    override val measures get() = _measures as List<Measure>

    override fun setName(name: String) {
        _name = name
    }

    override fun addMeasure(
        position: Int,
        measure: Measure,
    ) {
        val measureCopy = measure.clone()
        measureCopy.setTimeSignature(timeSignature)
        _measures.add(position, measureCopy)
    }

    override fun removeMeasure(position: Int): Boolean {
        if (position < 0 || position >= _measures.size) {
            return false
        }
        _measures.removeAt(position)
        return true
    }

    override fun setMeasures(measures: Collection<Measure>) {
        _measures.clear()
        _measures.addAll(measures)
        _measures.forEach { it.setTimeSignature(timeSignature) }
    }

    override fun setClef(clef: Clef) {
        _clef = clef
    }

    override fun setTimeSignature(timeSignature: TimeSignature) {
        _timeSignature = timeSignature

        _measures.forEach { it.setTimeSignature(timeSignature) }
    }

    override fun mutableMeasures(): List<Measure> = measures.map { it.clone() }

    override fun mutateMeasures(block: (List<Measure>) -> Unit) {
        block(measures)
    }
}
