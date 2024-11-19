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
import org.goalteam.tunelint.view.viewable.MelodyViewable

class MelodyViewableImpl(
    private val melody: Melody,
) : ImmutableMelody by melody,
    MelodyViewable {
    override var snapshot: ImmutableMelodyViewable
        by mutableStateOf(ImmutableMelodyViewableImpl(this))

    @Composable
    override fun view() =
        MelodyView(
            snapshot,
            GeometryData(20, 20, 50, 50),
        )

    override fun clone() = MelodyViewableImpl(melody.clone())

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

    override fun removeMeasure(position: Int) {
        melody.removeMeasure(position)
        takeSnapshot()
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

    private fun takeSnapshot() {
        snapshot = ImmutableMelodyViewableImpl(this)
    }
}
