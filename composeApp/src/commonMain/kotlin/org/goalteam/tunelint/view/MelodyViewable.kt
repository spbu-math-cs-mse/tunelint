package org.goalteam.tunelint.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.goalteam.tunelint.model.core.Melody

@Composable
fun MelodyView(
    melody: Melody,
    geometryData: GeometryData,
) {
    val horizontalSize = melody.measures().size * 9 + 1

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
                .measures()
                .forEach {
                    (it as MeasureViewable)
                        .view()
                }
        }
    }
}

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

class MelodyViewable(
    melody: Melody,
) : Melody by melody,
    Viewable {
    @Composable
    override fun view() =
        MelodyView(
            this,
            GeometryData(20, 20, 50, 50),
        )
}
