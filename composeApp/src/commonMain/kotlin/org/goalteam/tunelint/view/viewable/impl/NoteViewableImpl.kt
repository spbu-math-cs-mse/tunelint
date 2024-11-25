package org.goalteam.tunelint.view.viewable.impl

import androidx.compose.runtime.Composable
import org.goalteam.tunelint.model.core.Note
import org.goalteam.tunelint.view.GeometryData
import org.goalteam.tunelint.view.composable.NoteView
import org.goalteam.tunelint.view.viewable.NoteViewable
import org.goalteam.tunelint.view.viewable.Viewable

class NoteViewableImpl(
    note: Note,
) : NoteViewable,
    Note by note,
    Viewable {
    @Composable
    override fun view(geometryData: GeometryData) =
        NoteView(
            this,
            geometryData,
        )

    override fun horizontalSteps() = 3
}
