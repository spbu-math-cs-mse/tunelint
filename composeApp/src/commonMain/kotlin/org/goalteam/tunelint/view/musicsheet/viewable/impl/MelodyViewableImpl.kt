package org.goalteam.tunelint.view.musicsheet.viewable.impl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.goalteam.tunelint.model.core.Clef
import org.goalteam.tunelint.model.core.ImmutableMelody
import org.goalteam.tunelint.model.core.Measure
import org.goalteam.tunelint.model.core.Melody
import org.goalteam.tunelint.model.core.TimeSignature
import org.goalteam.tunelint.view.musicsheet.ExternalEvaluatableGeometryData
import org.goalteam.tunelint.view.musicsheet.composable.MelodyView
import org.goalteam.tunelint.view.musicsheet.viewable.ImmutableMelodyViewable
import org.goalteam.tunelint.view.musicsheet.viewable.MelodyViewable
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel

class MelodyViewableImpl(
    private val melody: Melody,
) : ImmutableMelody by melody,
    MelodyViewable {
    override var snapshot: ImmutableMelodyViewable
        by mutableStateOf(ImmutableMelodyViewableImpl(this))

    @Composable
    override fun view(
        vm: RedactorScreenViewModel,
        geometryData: ExternalEvaluatableGeometryData,
    ) = MelodyView(
        vm,
        snapshot,
        geometryData,
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

    override fun removeMeasure(position: Int): Boolean {
        val success = melody.removeMeasure(position)
        takeSnapshot()
        return success
    }

    override fun setMeasures(measures: Collection<Measure>) {
        melody.setMeasures(measures.map { MeasureViewableImpl(it) })
        takeSnapshot()
    }

    override fun setClef(clef: Clef) {
        melody.setClef(clef)
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
