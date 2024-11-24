package org.goalteam.tunelint.view.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.goalteam.tunelint.model.core.Note
import org.goalteam.tunelint.model.core.PrimaryNoteValue
import org.goalteam.tunelint.view.GeometryData

@Composable
fun NoteView(
    note: Note,
    geometryData: GeometryData,
) {
    val topOffset =
        geometryData.firstLineOffset -
            geometryData.verticalStep * note.stage().value / 2 -
            geometryData.verticalStep

    Box(
        modifier =
            Modifier
                .size(
                    width = geometryData.horizontalStep * (note.value() / PrimaryNoteValue.Eighth.value()),
                    height = geometryData.fullHeight,
                ),
        // .border( TODO REMOVE COMMENTED
        //    width = 2.dp,
        //    color = Color.Black,
        //    shape = RectangleShape,
        // ),
    ) {
        Box(
            modifier =
                Modifier
                    .offset(y = topOffset)
                    .size(size = geometryData.verticalStep)
                    .background(color = Color.Red),
        )
    }
}
