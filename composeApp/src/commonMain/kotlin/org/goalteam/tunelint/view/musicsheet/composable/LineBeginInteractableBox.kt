package org.goalteam.tunelint.view.musicsheet.composable

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import org.goalteam.tunelint.interaction.events.Action
import org.goalteam.tunelint.interaction.events.Side
import org.goalteam.tunelint.model.core.NoteOffset
import org.goalteam.tunelint.view.musicsheet.InternalGeometryData
import org.goalteam.tunelint.view.musicsheet.firstLineOffset
import org.goalteam.tunelint.view.musicsheet.fullHeight
import org.goalteam.tunelint.view.musicsheet.lineBeginMargin
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel
import kotlin.math.round

@Composable
fun LineBeginInteractableBox(
    vm: RedactorScreenViewModel,
    geometryData: InternalGeometryData,
    measure: Int,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier =
            Modifier
                .size(
                    width = geometryData.lineBeginMargin,
                    height = geometryData.fullHeight,
                ).pointerInput(Unit) {
                    detectTapGestures { offset: Offset ->
                        val stage =
                            NoteOffset(
                                round(
                                    (geometryData.firstLineOffset.toPx() - offset.y) /
                                        geometryData.verticalStep.toPx() * 2,
                                ).toInt() - 1,
                            )
                        vm.interactor.handleAction(
                            stage,
                            0,
                            measure,
                            Side.Left,
                            action = Action.Click,
                        )
                    }
                },
    ) {
        content()
    }
}
