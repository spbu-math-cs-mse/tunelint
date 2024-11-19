package org.goalteam.tunelint.model.core.impl

import org.goalteam.tunelint.model.core.Measure
import org.goalteam.tunelint.model.core.Melody
import org.goalteam.tunelint.model.core.TimeSignature

internal class MelodyImpl(
    name: String,
    timeSignature: TimeSignature,
    measures: Collection<Measure>,
) : Melody {
    private var _name = name
    private var _timeSignature = timeSignature
    private val _measures: MutableList<Measure> = measures.toMutableList()

    override fun clone() =
        MelodyImpl(
            name,
            timeSignature,
            _measures.toList(),
        )

    override val name get() = _name
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

    override fun removeMeasure(position: Int) {
        _measures.removeAt(position)
    }

    override fun setMeasures(measures: Collection<Measure>) {
        _measures.clear()
        _measures.addAll(measures)
        _measures.forEach { it.setTimeSignature(timeSignature) }
    }

    override fun setTimeSignature(timeSignature: TimeSignature) {
        _timeSignature = timeSignature

        _measures.forEach { it.setTimeSignature(timeSignature) }
    }

    override fun mutableMeasures(): List<Measure> = _measures
}
