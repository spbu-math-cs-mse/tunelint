package org.goalteam.tunelint.view.musicsheet.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.goalteam.tunelint.view.musicsheet.GeometryData
import org.goalteam.tunelint.view.musicsheet.viewable.ImmutableMelodyViewable
import org.goalteam.tunelint.view.musicsheet.viewable.MeasureViewable
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel

@Composable
fun MelodyView(
    vm: RedactorScreenViewModel,
    melody: ImmutableMelodyViewable,
    geometryData: GeometryData,
) {
    val horizontalSize =
        melody.measures.size * (melody.measureHorizontalSteps() + 3)

    Box(
        modifier =
            Modifier
                .size(
                    width = geometryData.horizontalStep * horizontalSize,
                    height = geometryData.fullHeight,
                ),
    ) {
        Staff(geometryData, 3.dp)

        Row {
            FullHeightBox(geometryData, 1) {}
            melody
                .measures
                .forEachIndexed { i, it ->
                    (it as MeasureViewable).view(vm, i, geometryData)
                    MeasureLine(vm, it.symbols.size, i, geometryData, 5.dp)
                }
        }
    }
}
