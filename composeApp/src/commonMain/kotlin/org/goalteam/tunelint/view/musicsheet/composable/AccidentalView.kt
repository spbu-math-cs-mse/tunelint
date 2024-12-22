package org.goalteam.tunelint.view.musicsheet.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.goalteam.tunelint.model.core.Accidental
import org.goalteam.tunelint.view.musicsheet.InternalGeometryData
import org.goalteam.tunelint.view.musicsheet.viewable.NoteViewable

@Composable
fun AccidentalView(
    geometryData: InternalGeometryData,
    note: NoteViewable,
) {
    Box(
        modifier =
            Modifier.requiredSize(
                width = geometryData.verticalStep,
                height = geometryData.verticalStep * 2,
            ),
    ) {
        when (note.accidental()) {
            is Accidental.Sharp -> SharpView()
            is Accidental.Flat -> FlatView()
            is Accidental.Natural -> NaturalView()
            null -> {}
        }
    }
}
