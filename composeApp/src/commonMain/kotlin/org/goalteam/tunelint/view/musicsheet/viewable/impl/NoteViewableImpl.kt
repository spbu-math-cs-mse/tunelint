package org.goalteam.tunelint.view.musicsheet.viewable.impl

import androidx.compose.runtime.Composable
import org.goalteam.tunelint.model.core.Note
import org.goalteam.tunelint.view.musicsheet.GeometryData
import org.goalteam.tunelint.view.musicsheet.composable.NoteView
import org.goalteam.tunelint.view.musicsheet.viewable.NoteViewable
import org.goalteam.tunelint.view.musicsheet.viewable.hasFlag
import org.goalteam.tunelint.view.musicsheet.viewable.isStemDown
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel

class NoteViewableImpl(
    note: Note,
) : NoteViewable,
    Note by note {
    override fun stepsBeforeMiddle(): Int {
        if (!isStemDown() && !hasFlag()) {
            return 1
        }
        return 2
    }

    override fun stepsAfterMiddle(): Int {
        if (isStemDown() && !hasFlag()) {
            return 1
        }
        return 2
    }

    @Composable
    override fun view(
        vm: RedactorScreenViewModel,
        position: Int,
        measure: Int,
        geometryData: GeometryData,
    ) = NoteView(
        vm,
        position,
        measure,
        this,
        geometryData,
    )
}
