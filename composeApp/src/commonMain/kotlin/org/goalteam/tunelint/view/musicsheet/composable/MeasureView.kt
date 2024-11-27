package org.goalteam.tunelint.view.musicsheet.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.unit.times
import org.goalteam.tunelint.interaction.events.Action
import org.goalteam.tunelint.interaction.events.Side
import org.goalteam.tunelint.model.core.NoteOffset
import org.goalteam.tunelint.view.musicsheet.GeometryData
import org.goalteam.tunelint.view.musicsheet.viewable.ImmutableMeasureViewable
import org.goalteam.tunelint.view.musicsheet.viewable.SymbolViewable
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel
import kotlin.math.round

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MeasureView(
    vm: RedactorScreenViewModel,
    index: Int,
    measure: ImmutableMeasureViewable,
    steps: Int,
    geometryData: GeometryData,
) {
    Box(
        modifier =
            Modifier
                .size(
                    width = geometryData.horizontalStep * steps,
                    height = geometryData.fullHeight,
                ),
    ) {
        Row {
            measure.symbols.forEachIndexed { i, it ->
                (it as SymbolViewable).view(vm, i, index, geometryData)
            }
            Box(
                modifier =
                    Modifier
                        .size(
                            width = (steps - measure.horizontalSteps()) * geometryData.horizontalStep * steps,
                            height = geometryData.fullHeight,
                        ).onPointerEvent(PointerEventType.Press) {
                            val stage =
                                NoteOffset(
                                    round(
                                        (geometryData.firstLineOffset.toPx() - it.changes[0].position.y) /
                                            geometryData.verticalStep.toPx() * 2,
                                    ).toInt() - 1,
                                )
                            val left = true
                            val side = if (left) Side.Left else Side.Right
                            println("$stage, $left")
                            vm.interactor.handleAction(
                                stage,
                                measure.symbols.size,
                                index,
                                side,
                                action = Action.Click,
                            )
                        },
            ) {
            }
        }
    }
}
