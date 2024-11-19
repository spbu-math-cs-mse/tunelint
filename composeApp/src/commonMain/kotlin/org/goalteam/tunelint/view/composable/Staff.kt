package org.goalteam.tunelint.view.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import org.goalteam.tunelint.view.GeometryData

@Composable
fun Staff(
    geometryData: GeometryData,
    width: Dp,
) {
    val offset = (geometryData.fullHeight - geometryData.verticalStep * 5) / 2
    Box {
        Column(
            modifier =
                Modifier
                    .padding(vertical = offset),
        ) {
            repeat(5) {
                Box(
                    modifier =
                        Modifier
                            .padding(vertical = (geometryData.verticalStep - width) / 2)
                            .size(width = geometryData.horizontalStep * 28, height = width)
                            .background(Color.Black),
                )
            }
        }
    }
}
