package org.goalteam.tunelint.view.musicsheet.composable

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.debugInspectorInfo
import org.goalteam.tunelint.interaction.events.Action
import org.goalteam.tunelint.interaction.events.Side
import org.goalteam.tunelint.model.core.NoteOffset
import org.goalteam.tunelint.view.musicsheet.InternalGeometryData
import org.goalteam.tunelint.view.musicsheet.firstLineOffset
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel
import kotlin.math.round

fun PointerInputScope.pointerEventHandler(
    vm: RedactorScreenViewModel,
    geometryData: InternalGeometryData,
    leftSteps: Int,
    rightSteps: Int,
    position: Int,
    measure: Int,
): ((Offset) -> Unit) =
    { offset ->
        val stage =
            NoteOffset(
                round(
                    (geometryData.firstLineOffset.toPx() - offset.y) /
                        geometryData.verticalStep.toPx() * 2,
                ).toInt() - 1,
            )
        val side =
            if
                (offset.x < leftSteps * geometryData.horizontalStep.toPx()) {
                Side.Left
            } else {
                Side.Right
            }
        vm.interactor.handleAction(
            stage,
            position,
            measure,
            side,
            action = Action.Click,
        )
    }

@Composable
fun Modifier.interactable(
    vm: RedactorScreenViewModel,
    geometryData: InternalGeometryData,
    leftSteps: Int,
    rightSteps: Int,
    position: Int,
    measure: Int,
) = this then
    pointerInput(Unit) {
        val lambda =
            pointerEventHandler(vm, geometryData, leftSteps, rightSteps, position, measure)
        detectTapGestures(onTap = lambda)
    }

@Composable
fun InteractableBox(
    vm: RedactorScreenViewModel,
    geometryData: InternalGeometryData,
    leftSteps: Int,
    rightSteps: Int,
    position: Int,
    measure: Int,
    content: @Composable BoxScope.() -> Unit,
) {
    FullHeightBox(geometryData, leftSteps + rightSteps) {
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .composed(
                        inspectorInfo =
                            debugInspectorInfo {
                                name = "interactable"
                                value = position
                            },
                        factory = { interactable(vm, geometryData, leftSteps, rightSteps, position, measure) },
                    ),
        ) {
            content()
        }
    }
}
