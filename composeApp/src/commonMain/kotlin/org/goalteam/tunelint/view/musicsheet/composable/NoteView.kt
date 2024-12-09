package org.goalteam.tunelint.view.musicsheet.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import org.goalteam.tunelint.interaction.events.Action
import org.goalteam.tunelint.interaction.events.Side
import org.goalteam.tunelint.model.core.NoteOffset
import org.goalteam.tunelint.view.musicsheet.InternalGeometryData
import org.goalteam.tunelint.view.musicsheet.firstLineOffset
import org.goalteam.tunelint.view.musicsheet.fullHeight
import org.goalteam.tunelint.view.musicsheet.viewable.NoteViewable
import org.goalteam.tunelint.view.musicsheet.viewable.horizontalSteps
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel
import kotlin.math.round

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NoteView(
    vm: RedactorScreenViewModel,
    position: Int,
    measure: Int,
    note: NoteViewable,
    geometryData: InternalGeometryData,
) {
    val topOffset =
        geometryData.firstLineOffset -
            geometryData.verticalStep * note.stage().value / 2 -
            geometryData.verticalStep
    FullHeightBox(geometryData, note.horizontalSteps()) {
        Box(
            modifier =
                Modifier
                    .matchParentSize()
                    .onPointerEvent(PointerEventType.Press) {
                        val stage =
                            NoteOffset(
                                round(
                                    (
                                        geometryData.firstLineOffset.toPx() -
                                            it.changes[0].position.y
                                    ) /
                                        geometryData.verticalStep.toPx() * 2,
                                ).toInt() - 1,
                            )
                        val left =
                            it.changes[0].position.x < note.stepsBeforeMiddle() * geometryData.horizontalStep.toPx()
                        val side = if (left) Side.Left else Side.Right
                        println("$stage, $left, $measure")
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
                            width = geometryData.horizontalStep * note.horizontalSteps(),
                            height = geometryData.fullHeight,
                        ).offset(
                            y = topOffset,
                        ),
            ) {
                UnleveredNoteView(geometryData, note)
            }
        }
    }
}
