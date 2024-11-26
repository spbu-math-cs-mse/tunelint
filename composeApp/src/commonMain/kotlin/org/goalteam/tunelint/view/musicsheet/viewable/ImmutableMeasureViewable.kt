package org.goalteam.tunelint.view.musicsheet.viewable

import androidx.compose.runtime.Composable
import org.goalteam.tunelint.model.core.ImmutableMeasure
import org.goalteam.tunelint.view.musicsheet.GeometryData
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel

interface ImmutableMeasureViewable : ImmutableMeasure {
    @Composable
    fun view(
        vm: RedactorScreenViewModel,
        index: Int,
        geometryData: GeometryData,
    )

    fun horizontalSteps(): Int
}
