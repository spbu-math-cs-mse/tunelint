package org.goalteam.tunelint.view.musicsheet.viewable.impl

import androidx.compose.runtime.Composable
import org.goalteam.tunelint.model.core.Note
import org.goalteam.tunelint.model.core.Symbol
import org.goalteam.tunelint.view.musicsheet.InternalGeometryData
import org.goalteam.tunelint.view.musicsheet.composable.NoteView
import org.goalteam.tunelint.view.musicsheet.viewable.NoteViewable
import org.goalteam.tunelint.view.musicsheet.viewable.hasFlag
import org.goalteam.tunelint.view.musicsheet.viewable.isStemDown
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel

class NoteViewableImpl(
    private val note: Note,
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

    override fun delegate(): Symbol = note

    @Composable
    override fun view(
        vm: RedactorScreenViewModel,
        position: Int,
        measure: Int,
        geometryData: InternalGeometryData,
    ) = NoteView(
        vm,
        position,
        measure,
        this,
        geometryData,
    )
}
