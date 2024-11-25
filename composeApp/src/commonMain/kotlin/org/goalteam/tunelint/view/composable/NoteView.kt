package org.goalteam.tunelint.view.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.goalteam.tunelint.view.GeometryData
import org.goalteam.tunelint.view.viewable.NoteViewable

@Composable
fun NoteView(
    note: NoteViewable,
    geometryData: GeometryData,
) {
    val topOffset =
        geometryData.firstLineOffset -
            geometryData.verticalStep * note.stage().value / 2 -
            geometryData.verticalStep
    FullHeightBox(geometryData, note.horizontalSteps()) {
        Box(
            modifier =
                Modifier
                    .offset(y = topOffset),
        ) {
            UnleveredNoteView(geometryData, note)
        }
    }
}
