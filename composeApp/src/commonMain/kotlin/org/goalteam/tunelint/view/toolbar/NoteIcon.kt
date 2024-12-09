package org.goalteam.tunelint.view.toolbar

import androidx.compose.runtime.Composable
import org.goalteam.tunelint.model.core.MusicFactory
import org.goalteam.tunelint.model.core.NoteOffset
import org.goalteam.tunelint.model.core.PrimaryNoteValue
import org.goalteam.tunelint.view.musicsheet.GeometryData
import org.goalteam.tunelint.view.musicsheet.composable.UnleveredNoteView
import org.goalteam.tunelint.view.musicsheet.viewable.impl.NoteViewableImpl

@Composable
fun NoteIcon(value: Int) {
    UnleveredNoteView(
        GeometryData(6, 8, 5, 0),
        note = NoteViewableImpl(MusicFactory().createNote(NoteOffset(1), PrimaryNoteValue(value))),
    )
}
