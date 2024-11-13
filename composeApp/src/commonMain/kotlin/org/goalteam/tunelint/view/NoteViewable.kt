package org.goalteam.tunelint.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.goalteam.tunelint.model.core.Note

@Composable
fun NoteView(
    note: Note,
    geometryData: GeometryData,
) {
    val topOffset =
        geometryData.firstLineOffset -
            geometryData.verticalStep * note.pitch() / 2 -
            geometryData.verticalStep

    Box(
        modifier =
            Modifier
                .size(
                    width = geometryData.horizontalStep * note.value().toInt(),
                    height = geometryData.fullHeight,
                ),
        // .border(
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

class NoteViewable(
    note: Note,
) : SymbolViewable,
    Note by note,
    Viewable {
    @Composable
    override fun view() =
        NoteView(
            this,
            GeometryData(20, 20, 50, 50),
        )
}
