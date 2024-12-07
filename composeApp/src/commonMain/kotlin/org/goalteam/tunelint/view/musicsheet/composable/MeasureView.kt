package org.goalteam.tunelint.view.musicsheet.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.goalteam.tunelint.view.musicsheet.InternalGeometryData
import org.goalteam.tunelint.view.musicsheet.fullHeight
import org.goalteam.tunelint.view.musicsheet.viewable.ImmutableMeasureViewable
import org.goalteam.tunelint.view.musicsheet.viewable.SymbolViewable
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel

@Composable
fun MeasureView(
    vm: RedactorScreenViewModel,
    index: Int,
    measure: ImmutableMeasureViewable,
    geometryData: InternalGeometryData,
) {
    Box(
        modifier =
            Modifier
                .size(
                    width = geometryData.horizontalStep * (measure.horizontalSteps()),
                    height = geometryData.fullHeight,
                ),
    ) {
        Row {
            measure.symbols.forEachIndexed { i, it ->
                (it as SymbolViewable).view(vm, i, index, geometryData)
            }
        }
    }
}
