package org.goalteam.tunelint.view.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.times
import org.goalteam.tunelint.view.GeometryData

@Composable
fun StaffLine(
    geometryData: GeometryData,
    width: Dp,
) {
    Box(
        modifier =
            Modifier
                .padding(vertical = 0.5 * (geometryData.verticalStep - width))
                .size(width = geometryData.horizontalStep * 28, height = width)
                .background(Color.Black),
    )
}

@Composable
fun Staff(
    geometryData: GeometryData,
    width: Dp,
) {
    Box(
        modifier =
            Modifier
                .offset(y = geometryData.topMargin),
    ) {
        for (i in 0..4) {
            Box(
                modifier =
                    Modifier
                        .offset(y = i * geometryData.verticalStep),
            ) {
                StaffLine(geometryData, width)
            }
        }
    }
}
