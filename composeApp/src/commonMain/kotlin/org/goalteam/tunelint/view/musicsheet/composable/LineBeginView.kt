package org.goalteam.tunelint.view.musicsheet.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import org.goalteam.tunelint.view.musicsheet.InternalGeometryData
import org.goalteam.tunelint.view.musicsheet.viewable.ImmutableMelodyViewable
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel

@Composable
fun LineBeginView(
    vm: RedactorScreenViewModel,
    melody: ImmutableMelodyViewable,
    geometryData: InternalGeometryData,
    measure: Int,
) {
    LineBeginInteractableBox(vm, geometryData, measure) {
        Row {
            ClefView(melody, geometryData)
            TimeSignatureView(melody, geometryData)
        }
    }
}
