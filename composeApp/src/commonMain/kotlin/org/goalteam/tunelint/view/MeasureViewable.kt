package org.goalteam.tunelint.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.goalteam.tunelint.model.core.Measure
import org.goalteam.tunelint.model.core.MutableMeasure

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

@Composable
fun MeasureView(
    measure: Measure,
    geometryData: GeometryData,
) {
    val stepWidth = measure.symbols().sumOf { it.value().toInt() } + 1

    Box(
        modifier =
            Modifier
                .size(width = geometryData.horizontalStep * stepWidth, height = geometryData.fullHeight),
    ) {
        Row {
            measure.symbols().forEach { (it as SymbolViewable).view() }
            MeasureLine(geometryData, 10.dp)
        }
    }
}

class MeasureViewable(
    measure: MutableMeasure, // TODO: change to Measure
) : MutableMeasure by measure,
    Viewable {
    @Composable
    override fun view() =
        MeasureView(
            this,
            GeometryData(20, 20, 50, 50),
        )
}
