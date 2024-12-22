package org.goalteam.tunelint.view.musicsheet.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import org.goalteam.tunelint.view.musicsheet.InternalGeometryData
import org.goalteam.tunelint.view.musicsheet.firstLineOffset
import org.goalteam.tunelint.view.musicsheet.fullHeight
import org.goalteam.tunelint.view.musicsheet.viewable.NoteViewable
import org.goalteam.tunelint.view.musicsheet.viewable.horizontalSteps
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel

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
    InteractableBox(
        vm,
        geometryData,
        note.stepsBeforeMiddle(),
        note.stepsAfterMiddle(),
        position,
        measure,
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
