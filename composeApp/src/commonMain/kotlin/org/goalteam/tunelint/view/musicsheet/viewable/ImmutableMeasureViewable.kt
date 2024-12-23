package org.goalteam.tunelint.view.musicsheet.viewable

import androidx.compose.runtime.Composable
import org.goalteam.tunelint.model.core.ImmutableMeasure
import org.goalteam.tunelint.model.core.Measure
import org.goalteam.tunelint.view.musicsheet.InternalGeometryData
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel

interface ImmutableMeasureViewable : ImmutableMeasure {
    @Composable
    fun view(
        vm: RedactorScreenViewModel,
        index: Int,
        geometryData: InternalGeometryData,
    )

    fun delegate(): Measure

    fun horizontalSteps(): Int
}

fun ImmutableMeasureViewable.origin() =
    delegate().run {
        if (this is ImmutableMeasureViewable) delegate() else this
    }
