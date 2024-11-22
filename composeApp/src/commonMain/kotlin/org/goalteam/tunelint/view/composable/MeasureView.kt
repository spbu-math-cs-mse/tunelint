package org.goalteam.tunelint.view.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.goalteam.tunelint.model.core.PrimaryNoteValue
import org.goalteam.tunelint.model.core.totalValue
import org.goalteam.tunelint.view.GeometryData
import org.goalteam.tunelint.view.viewable.ImmutableMeasureViewable
import org.goalteam.tunelint.view.viewable.SymbolViewable

@Composable
fun MeasureView(
    measure: ImmutableMeasureViewable,
    geometryData: GeometryData,
) {
    val stepWidth = measure.totalValue / PrimaryNoteValue.Eighth.value() * 2
    Box(
        modifier =
            Modifier
                .size(
                    width = geometryData.horizontalStep * stepWidth,
                    height = geometryData.fullHeight,
                ),
    ) {
        Row {
            measure.symbols.forEach { (it as SymbolViewable).view() }
        }
    }
}
