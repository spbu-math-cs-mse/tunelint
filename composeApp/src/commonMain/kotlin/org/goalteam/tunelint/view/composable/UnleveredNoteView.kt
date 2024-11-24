package org.goalteam.tunelint.view.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.goalteam.tunelint.model.core.PrimaryNoteValue
import org.goalteam.tunelint.view.GeometryData
import org.goalteam.tunelint.view.viewable.NoteViewable

@Composable
fun UnleveredNoteView(
    geometryData: GeometryData,
    note: NoteViewable,
) {
    Box(
        modifier =
            Modifier
                .size(
                    height = geometryData.verticalStep,
                    width = geometryData.verticalStep * 2,
                ),
    ) {
        if (note.primaryValue() < PrimaryNoteValue.Whole) {
            if (note.stage().value > 3) {
                BottomNoteStem(geometryData)
            } else {
                TopNoteStem(geometryData)
            }
        }

        if (note.primaryValue() >= PrimaryNoteValue.Whole) {
            WholeNote()
        }

        if (note.primaryValue() == PrimaryNoteValue.Half) {
            WhiteNoteHead()
        }

        if (note.primaryValue() <= PrimaryNoteValue.Quarter) {
            BlackNoteHead()
        }

        if (note.primaryValue() <= PrimaryNoteValue.Eighth) {
            if (note.stage().value > 3) {
                BottomNoteFlag(geometryData, 0)
            } else {
                TopNoteFlag(geometryData, 0)
            }
        }
    }
}
