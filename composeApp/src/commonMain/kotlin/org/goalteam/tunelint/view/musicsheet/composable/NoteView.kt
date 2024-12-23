package org.goalteam.tunelint.view.musicsheet.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.times
import org.goalteam.tunelint.view.musicsheet.InternalGeometryData
import org.goalteam.tunelint.view.musicsheet.firstLineOffset
import org.goalteam.tunelint.view.musicsheet.viewable.NoteViewable
import org.goalteam.tunelint.view.musicsheet.viewable.origin
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel

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
        val styles = vm.styles.value
        Box(
            modifier =
                Modifier
                    .offset(
                        y = topOffset - geometryData.verticalStep / 2,
                        x =
                            geometryData.horizontalStep * note.stepsBeforeMiddle() -
                                1.5 * geometryData.verticalStep,
                    ).then(styles.find(note.accidental())),
        ) {
            AccidentalView(geometryData, note)
        }
        Box(modifier = Modifier.offset(y = topOffset)) {
            UnleveredNoteView(geometryData, note, styles.find(note.origin()))
        }
    }
}
