package org.goalteam.tunelint.view.musicsheet.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.goalteam.tunelint.view.musicsheet.ExternalEvaluatableGeometryData
import org.goalteam.tunelint.view.musicsheet.InternalGeometryData
import org.goalteam.tunelint.view.musicsheet.fullHeight
import org.goalteam.tunelint.view.musicsheet.viewable.ImmutableMelodyViewable
import org.goalteam.tunelint.view.musicsheet.viewable.MeasureViewable
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel

internal fun splitMeasuresOnLines(
    melody: ImmutableMelodyViewable,
    geometryData: ExternalEvaluatableGeometryData,
): List<List<MeasureViewable>> {
    val lines = mutableListOf(mutableListOf<MeasureViewable>())

    var stepsOnLine = 0

    melody.measures.forEach {
        val mv = it as MeasureViewable

        if (mv.horizontalSteps() > geometryData.maxSteps) {
            throw Exception("too long measure")
        }

        if (stepsOnLine + mv.horizontalSteps() + 1 <= geometryData.maxSteps) {
            stepsOnLine += mv.horizontalSteps() + 1
            lines.last().add(mv)
        } else {
            stepsOnLine = mv.horizontalSteps() + 1
            lines.add(mutableListOf())
            lines.last().add(mv)
        }
    }

    return lines
}

@Composable
fun MelodyView(
    vm: RedactorScreenViewModel,
    melody: ImmutableMelodyViewable,
    geometryData: ExternalEvaluatableGeometryData,
) {
    val lines = splitMeasuresOnLines(melody, geometryData)
    val internals = mutableListOf<InternalGeometryData>()
    lines.forEach {
        internals.add(
            geometryData.evaluated(
                it.sumOf { m -> m.horizontalSteps() + 1 },
            ),
        )
    }

    Box(
        modifier =
            Modifier
                .size(
                    width = geometryData.fullWidth,
                    height = geometryData.fullHeight * lines.size,
                ),
    ) {
        Column(
            modifier =
                Modifier
                    .padding(start = geometryData.leftMargin, end = geometryData.rightMargin),
        ) {
            var globalIndex = 0

            lines.forEachIndexed { index, line ->
                Box {
                    Staff(geometryData, 3.dp)
                    val internal = internals[index]
                    Row {
                        LineBeginView(vm, melody, internal, globalIndex)
                        line
                            .forEach {
                                it.view(vm, globalIndex, internal)
                                MeasureLine(vm, it.symbols.size, globalIndex, internal, 5.dp)
                                globalIndex++
                            }
                    }
                }
            }
        }
    }
}
