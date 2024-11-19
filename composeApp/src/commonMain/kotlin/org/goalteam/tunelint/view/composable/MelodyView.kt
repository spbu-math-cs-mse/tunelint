package org.goalteam.tunelint.view.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.goalteam.tunelint.view.GeometryData
import org.goalteam.tunelint.view.viewable.ImmutableMelodyViewable
import org.goalteam.tunelint.view.viewable.Viewable

@Composable
fun MelodyView(
    melody: ImmutableMelodyViewable,
    geometryData: GeometryData,
) {
    val horizontalSize = melody.measures.size * 9 + 1

    Box(
        modifier =
            Modifier
                .size(
                    width = geometryData.horizontalStep * horizontalSize,
                    height = geometryData.fullHeight,
                ),
    ) {
        Staff(geometryData, 5.dp)

        Row {
            Box(
                modifier =
                    Modifier
                        .size(width = 20.dp, height = 150.dp),
            )
            melody
                .measures
                .forEach {
                    (it as Viewable)
                        .view()
                    MeasureLine(geometryData, 10.dp)
                }
        }
    }
}
