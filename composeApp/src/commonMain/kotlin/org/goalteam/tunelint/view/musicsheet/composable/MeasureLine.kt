package org.goalteam.tunelint.view.musicsheet.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.unit.Dp
import org.goalteam.tunelint.interaction.events.Action
import org.goalteam.tunelint.interaction.events.Side
import org.goalteam.tunelint.model.core.NoteOffset
import org.goalteam.tunelint.view.musicsheet.GeometryData
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel
import kotlin.math.round

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MeasureLine(
    vm: RedactorScreenViewModel,
    position: Int,
    measure: Int,
    geometryData: GeometryData,
    width: Dp,
) {
    Box(
        modifier =
            Modifier
                .size(
                    width = geometryData.horizontalStep,
                    height = geometryData.fullHeight,
                ).onPointerEvent(PointerEventType.Press) {
                    val stage =
                        NoteOffset(
                            round(
                                (geometryData.firstLineOffset.toPx() - it.changes[0].position.y) /
                                    geometryData.verticalStep.toPx() * 2,
                            ).toInt() - 1,
                        )
                    val left = it.changes[0].position.x < geometryData.horizontalStep.toPx() / 2
                    val side = if (left) Side.Left else Side.Right
                    println("$stage, $left")
                    vm.interactor.handleAction(
                        stage,
                        if (left) position else -1,
                        measure + if (left) 0 else 1,
                        side,
                        action = Action.Click,
                    )
                },
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
