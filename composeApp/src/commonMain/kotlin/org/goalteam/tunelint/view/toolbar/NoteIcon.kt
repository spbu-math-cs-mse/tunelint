package org.goalteam.tunelint.view.toolbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.goalteam.tunelint.model.core.MusicFactory
import org.goalteam.tunelint.model.core.NoteOffset
import org.goalteam.tunelint.model.core.PrimaryNoteValue
import org.goalteam.tunelint.view.musicsheet.GeometryData
import org.goalteam.tunelint.view.musicsheet.composable.UnleveredNoteView
import org.goalteam.tunelint.view.musicsheet.viewable.horizontalSteps
import org.goalteam.tunelint.view.musicsheet.viewable.impl.NoteViewableImpl

@Composable
fun NoteIcon(value: Int) {
    val note = NoteViewableImpl(MusicFactory().createNote(NoteOffset(1), PrimaryNoteValue(value)))
    Box(modifier = Modifier.offset(y = 10.dp)) {
        UnleveredNoteView(
            geometryData =
                ExternalEvaluatableGeometryDataImpl(
                    verticalStep = 8.0,
                    upperMargin = 100.0,
                    minimalHorizontalStep = 2.0,
                    lowerMargin = 0.0,
                    fullWidth = 50.0,
                    leftMargin = 0.0,
                    rightMargin = 0.0,
                ).evaluated(note.horizontalSteps()),
            note = note,
        )
    }
}
