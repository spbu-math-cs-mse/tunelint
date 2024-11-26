package org.goalteam.tunelint.view.viewable.impl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.goalteam.tunelint.model.core.ImmutableMelody
import org.goalteam.tunelint.model.core.Measure
import org.goalteam.tunelint.model.core.Melody
import org.goalteam.tunelint.model.core.TimeSignature
import org.goalteam.tunelint.view.GeometryData
import org.goalteam.tunelint.view.composable.MelodyView
import org.goalteam.tunelint.view.viewable.ImmutableMelodyViewable
import org.goalteam.tunelint.view.viewable.MeasureViewable
import org.goalteam.tunelint.view.viewable.MelodyViewable

class MelodyViewableImpl(
    private val melody: Melody,
) : ImmutableMelody by melody,
    MelodyViewable {
    override var snapshot: ImmutableMelodyViewable
        by mutableStateOf(ImmutableMelodyViewableImpl(this))

    @Composable
    override fun view(geometryData: GeometryData) =
        MelodyView(
            snapshot,
            geometryData,
        )

    override fun clone() = MelodyViewableImpl(melody.clone())

    override fun measureHorizontalSteps() =
        measures.maxOfOrNull {
            (it as MeasureViewable).horizontalSteps()
        } ?: 1

    override fun setName(name: String) {
        melody.setName(name)
        takeSnapshot()
    }

    override fun addMeasure(
        position: Int,
        measure: Measure,
    ) {
        melody.addMeasure(position, MeasureViewableImpl(measure))
        takeSnapshot()
    }

    override fun removeMeasure(position: Int): Boolean {
        val success = melody.removeMeasure(position)
        takeSnapshot()
        return success
    }

    override fun setMeasures(measures: Collection<Measure>) {
        melody.setMeasures(measures)
        takeSnapshot()
    }

    override fun setTimeSignature(timeSignature: TimeSignature) {
        melody.setTimeSignature(timeSignature)
        takeSnapshot()
    }

    override fun mutableMeasures() = melody.mutableMeasures()

    override fun mutateMeasures(block: (List<Measure>) -> Unit) {
        melody.mutateMeasures(block)
        takeSnapshot()
    }

    private fun takeSnapshot() {
        snapshot = ImmutableMelodyViewableImpl(this)
    }
}
