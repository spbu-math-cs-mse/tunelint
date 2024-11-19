package org.goalteam.tunelint.view.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import org.goalteam.tunelint.view.GeometryData

@Composable
fun MeasureLine(
    geometryData: GeometryData,
    width: Dp,
) {
    Box(
        modifier =
            Modifier
                .size(
                    width = geometryData.horizontalStep,
                    height = geometryData.fullHeight,
                ),
    ) {
        Box(
            modifier =
                Modifier
                    .size(
                        width = width,
                        height = geometryData.staffShortHeight,
                    ).align(
                        alignment = Alignment.Center,
                    ).background(
                        Color.Black,
                    ),
        )
    }
}
