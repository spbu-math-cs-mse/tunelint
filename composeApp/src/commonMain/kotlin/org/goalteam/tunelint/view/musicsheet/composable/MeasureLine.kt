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
import org.goalteam.tunelint.view.musicsheet.InternalGeometryData
import org.goalteam.tunelint.view.musicsheet.firstLineOffset
import org.goalteam.tunelint.view.musicsheet.fullHeight
import org.goalteam.tunelint.view.musicsheet.staffShortHeight
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel
import kotlin.math.round

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MeasureLine(
    vm: RedactorScreenViewModel,
    position: Int,
    measure: Int,
    geometryData: InternalGeometryData,
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
                    val side = Side.Left
                    println("$stage")
                    vm.interactor.handleAction(
                        stage,
                        position,
                        measure,
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
                        alignment = Alignment.CenterEnd,
                    ).background(
                        Color.Black,
                    ),
        )
    }
}
